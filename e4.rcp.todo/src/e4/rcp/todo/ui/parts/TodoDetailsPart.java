/**
 * 
 */
package e4.rcp.todo.ui.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import e4.rcp.todo.model.ITodoModel;
import e4.rcp.todo.model.Todo;
import e4.rcp.todo.service.TodoService;

/**
 * @author TF016851
 * 
 */
public class TodoDetailsPart {

	@Inject
	MDirtyable dirty;

	@Inject
	EPartService service;

	private final class MyListener implements ModifyListener {
		@Override
		public void modifyText(final ModifyEvent e) {
			if (TodoDetailsPart.this.dirty != null) {
				TodoDetailsPart.this.dirty.setDirty(true);
			}
		}
	}

	Label label;
	private Text summary;
	private Text description;
	private Button btnDone;

	private Todo todo;
	private final MyListener listener = new MyListener();

	@PostConstruct
	public void createControls(final Composite parent) {
		System.out.println(this.getClass().getName()
				+ ".createCrontols(Composite parent)"); //$NON-NLS-1$
		parent.setLayout(new GridLayout(3, false));
		this.label = new Label(parent, SWT.NONE);

		final Label lblPriority = new Label(parent, SWT.NONE);
		lblPriority.setText(Messages.getString("TodoDetailsPart.1")); //$NON-NLS-1$

		final Combo combo = new Combo(parent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);

		final Label lblSummary = new Label(parent, SWT.NONE);
		final GridData gd_lblSummary = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_lblSummary.widthHint = 73;
		lblSummary.setLayoutData(gd_lblSummary);
		lblSummary.setText(Messages.getString("TodoDetailsPart.2")); //$NON-NLS-1$

		this.summary = new Text(parent, SWT.BORDER);
		this.summary.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		this.summary.addModifyListener(this.listener);
		new Label(parent, SWT.NONE);

		final Label lblDescription = new Label(parent, SWT.NONE);
		final GridData gd_lblDescription = new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 1);
		gd_lblDescription.widthHint = 92;
		lblDescription.setLayoutData(gd_lblDescription);
		lblDescription.setText(Messages.getString("TodoDetailsPart.3")); //$NON-NLS-1$

		this.description = new Text(parent, SWT.BORDER);
		final GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1);
		gd_text_1.heightHint = 88;
		this.description.setLayoutData(gd_text_1);
		this.description.addModifyListener(this.listener);
		new Label(parent, SWT.NONE);

		final Label lblDueDate = new Label(parent, SWT.NONE);
		lblDueDate.setText(Messages.getString("TodoDetailsPart.4")); //$NON-NLS-1$

		final DateTime dateTime = new DateTime(parent, SWT.BORDER);
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		final Button btnDone = new Button(parent, SWT.CHECK);
		btnDone.setText(Messages.getString("TodoDetailsPart.5")); //$NON-NLS-1$
	}

	@PreDestroy
	public void dispose() {

	}

	@Focus
	private void setFocus() {
		this.label.setFocus();
	}

	@Persist
	public void save(final MDirtyable dirty) {
		final ITodoModel model = TodoService.getInstance();
		this.todo.setSummary(this.summary.getText());
		this.todo.setDescription(this.description.getText());
		model.saveTodo(this.todo);
		dirty.setDirty(false);

		final MPart part = this.service
				.findPart("e4.rcp.todo.part.todooverview");
		final TodoOverviewPart obj = (TodoOverviewPart) part.getObject();
		obj.loadData();
	}

	@Inject
	public void setTodo(
			@Optional @Named(IServiceConstants.ACTIVE_SELECTION) final Todo todo) {
		if (todo != null) {
			// Remember the todo as field
			this.todo = todo;
			// update the user interface
			this.updateUserInterface(todo);
		}
	}

	private void updateUserInterface(final Todo todo) {

		if (this.summary != null) {
			this.summary.setText(todo.getSummary());
			this.description.setText(todo.getDescription());
			this.dirty.setDirty(false);
		}
	}
}
