package e4.rcp.todo.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import e4.rcp.todo.event.MyEventConstants;
import e4.rcp.todo.model.Todo;
import e4.rcp.todo.wizards.TodoWizard;

public class NewTodoHandler {

	@Execute
	public void execute(
			IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
			throws InvocationTargetException, InterruptedException {
		Todo todo = new Todo();
		WizardDialog dialog = new WizardDialog(shell, new TodoWizard(todo));
		if (dialog.open() == SWT.OK) {
//			model.saveTodo(todo);
//			// asynchronously
//			broker.post(MyEventConstants.TOPIC_TODO_DATA_UPDATE, "New data");
		}

		// Only for demo purposes
		// No receiver registered
//		broker.send(MyEventConstants.TOPIC_TODO_DATA_UPDATE, todo);
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}

}
