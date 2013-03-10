package e4.rcp.todo.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import e4.rcp.todo.model.Todo;
import e4.rcp.todo.ui.parts.TodoDetailsPart;

public class TodoWizardPage1 extends WizardPage {

	private Todo todo;

	public TodoWizardPage1(Todo todo) {
		super("page1");
		this.todo = todo;
		setTitle("New Todo");
		setDescription("Enter the todo data");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		// We reuse the part and 
        // inject the values
		TodoDetailsPart part = new TodoDetailsPart();
		part.createControls(container);
		part.setTodo(todo);
		setControl(container);
	}

}