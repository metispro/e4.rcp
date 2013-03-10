package e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

public class ExitHandler {

	@Execute
	public void execute(final IWorkbench workbench) {
		System.out.println("Closing workbench");
		workbench.close();
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}

}
