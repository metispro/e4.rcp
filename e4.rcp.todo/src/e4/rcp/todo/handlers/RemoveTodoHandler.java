package e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class RemoveTodoHandler {
	
	@Execute
	public void execute() {
		System.out.println(this.getClass().getName() + ".execute()");
	}

	@CanExecute
	public boolean canExecute() {
		return true;
	}

}
