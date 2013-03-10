/**
 * 
 */
package e4.rcp.todo.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import e4.rcp.todo.model.ITodoModel;
import e4.rcp.todo.model.Todo;
import e4.rcp.todo.service.TodoService;

/**
 * @author TF016851
 * 
 */
public class TodoOverviewPart {

	TableViewer tableViewer;
	Label label;
	private Table table;

	@Inject
	ESelectionService service;

	@Inject
	EPartService partService;

	@Inject
	EModelService modelService;

	ITodoModel model;

	@PostConstruct
	public void createControls(final Composite parent) {
		System.out.println(this.getClass().getName()
				+ ".createCrontols(Composite parent)"); //$NON-NLS-1$
		this.model = TodoService.getInstance();
		parent.setLayout(new GridLayout(2, false));

		final Button btnLoadData = new Button(parent, SWT.NONE);
		btnLoadData.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				TodoOverviewPart.this.loadData();
			}
		});
		btnLoadData.setText(Messages.getString("TodoOverviewPart.2")); //$NON-NLS-1$

		this.label = new Label(parent, SWT.NONE);
		final GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_label.widthHint = 188;
		this.label.setLayoutData(gd_label);
		this.label.setText(Messages.getString("TodoOverviewPart.3")); //$NON-NLS-1$

		this.tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		this.table = this.tableViewer.getTable();
		this.table.setLinesVisible(true);
		this.table.setHeaderVisible(true);
		this.table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1));

		this.createColumns(this.tableViewer);
		this.tableViewer.setContentProvider(new ArrayContentProvider());

		this.tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(
							final SelectionChangedEvent event) {

						final MPart part = TodoOverviewPart.this.partService
								.findPart("e4.rcp.todo.part.tododetails");
						part.setVisible(true);
						part.getParent().setVisible(true);
						TodoOverviewPart.this.partService.showPart(part,
								PartState.VISIBLE);

						final IStructuredSelection selection = (IStructuredSelection) TodoOverviewPart.this.tableViewer
								.getSelection();
						TodoOverviewPart.this.service.setSelection(selection
								.getFirstElement());

					}
				});

		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

	}

	private void createColumns(final TableViewer tableViewer) {
		final TableViewerColumn col1 = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn tblclmnId = col1.getColumn();
		tblclmnId.setMoveable(true);
		tblclmnId.setResizable(true);
		tblclmnId.setWidth(67);
		tblclmnId.setText(Messages.getString("TodoOverviewPart.4")); //$NON-NLS-1$
		col1.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(final Object element) {
				if (element instanceof Todo)
					return Long.toString(((Todo) element).getId());
				return super.getText(element);
			}

		});

		final TableViewerColumn col2 = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn tblclmnSummary = col2.getColumn();
		tblclmnSummary.setMoveable(true);
		tblclmnSummary.setResizable(true);
		tblclmnSummary.setWidth(100);
		tblclmnSummary.setText(Messages.getString("TodoOverviewPart.5")); //$NON-NLS-1$
		col2.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(final Object element) {
				if (element instanceof Todo)
					return ((Todo) element).getSummary();
				return super.getText(element);
			}

		});

		final TableViewerColumn col3 = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn tblclmnDescription = col3.getColumn();
		tblclmnDescription.setMoveable(true);
		tblclmnDescription.setResizable(true);
		tblclmnDescription.setWidth(100);
		tblclmnDescription.setText(Messages.getString("TodoOverviewPart.6")); //$NON-NLS-1$
		col3.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(final Object element) {
				if (element instanceof Todo)
					return ((Todo) element).getDescription();
				return super.getText(element);
			}

		});

		final TableViewerColumn col4 = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn tblclmnDueDate = col4.getColumn();
		tblclmnDueDate.setMoveable(true);
		tblclmnDueDate.setResizable(true);
		tblclmnDueDate.setWidth(100);
		tblclmnDueDate.setText(Messages.getString("TodoOverviewPart.7")); //$NON-NLS-1$
		col4.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(final Object element) {
				if (element instanceof Todo)
					return ((Todo) element).getDueDate().toString();
				return super.getText(element);
			}

		});

		final TableViewerColumn col5 = new TableViewerColumn(tableViewer,
				SWT.NONE);
		final TableColumn tblclmnDone = col5.getColumn();
		tblclmnDone.setMoveable(true);
		tblclmnDone.setResizable(true);
		tblclmnDone.setWidth(54);
		tblclmnDone.setText(Messages.getString("TodoOverviewPart.8")); //$NON-NLS-1$
		col5.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(final Object element) {
				if (element instanceof Todo)
					return Boolean.toString(((Todo) element).isDone());
				return super.getText(element);
			}

		});
	}

	public void loadData() {
		final List<Todo> todos = TodoOverviewPart.this.model.getTodos();
		TodoOverviewPart.this.label.setText(Messages
				.getString("TodoOverviewPart.1") + todos.size()); //$NON-NLS-1$
		TodoOverviewPart.this.tableViewer.setInput(TodoOverviewPart.this.model
				.getTodos());
	}

	@PreDestroy
	public void dispose() {

	}

	@Focus
	private void setFocus() {
		this.label.setFocus();
	}
}
