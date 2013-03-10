package e4.rcp.todo.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import e4.rcp.todo.model.ITodoModel;
import e4.rcp.todo.model.Todo;
import e4.rcp.todo.service.TodoService;

public class TodoDeletionPart {

	ITodoModel todoService;
	Button btnDeleteTodo;
	ComboViewer comboViewer;

	@PostConstruct
	public void createControls(Composite parent) {
		todoService = TodoService.getInstance();

		parent.setLayout(new GridLayout(2, false));

		comboViewer = new ComboViewer(parent, SWT.NONE);
		comboViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event.getSelection();
						System.out.println("SELECTED Todo: " //$NON-NLS-1$
								+ ((Todo) selection.getFirstElement())
										.getSummary());
					}
				});
		final Combo combo = comboViewer.getCombo();
		final GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_combo.widthHint = 149;
		combo.setLayoutData(gd_combo);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof Todo) {
					return ((Todo) element).getSummary();
				}
				return super.getText(element);
			}

		});

		updateViewer(todoService.getTodos());

		btnDeleteTodo = new Button(parent, SWT.PUSH);
		btnDeleteTodo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection sel = (IStructuredSelection) comboViewer.getSelection();
				if (sel.size() > 0) {
					Todo selectedTodo = (Todo) sel.getFirstElement();
					try {
						if (todoService.deleteTodo(selectedTodo.getId())) {
							System.out.println("DELETED Todo: " //$NON-NLS-1$
									+ selectedTodo.getSummary());
							updateViewer(todoService.getTodos());
						} else {
							System.out.println("FAILED TO DELETE Todo: " //$NON-NLS-1$
									+ selectedTodo.getSummary());
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeleteTodo.setText(Messages.getString("TodoDeletionPart.3")); //$NON-NLS-1$

	}
	
	private void updateViewer(List<Todo> todos) {
		if (todos != null && todos.size() > 0) {
			comboViewer.setInput(todos);
			comboViewer.setSelection(new StructuredSelection(todos.get(0)), true);
		}
	}

	@PreDestroy
	public void dispose() {

	}

	@Focus
	private void setFocus() {
		comboViewer.getCombo().setFocus();
	}
}
