package e4.rcp.todo.wizards;

import javax.inject.Inject;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import e4.rcp.todo.model.Todo;

public class TodoWizard extends Wizard {

private Todo todo;
	
	@Inject
	public TodoWizard(Todo todo) {
		this.todo = todo;
		setWindowTitle("New Wizard");
	}

	@Override
	public void addPages() {
		addPage(new TodoWizardPage1(todo));
		addPage(new TodoWizardPage2());
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public boolean canFinish() {
		return true;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		return super.getNextPage(page);
	}

}
