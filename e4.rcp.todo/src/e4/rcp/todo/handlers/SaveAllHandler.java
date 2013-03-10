package e4.rcp.todo.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SaveAllHandler {

	@Execute
	public void execute(final EPartService service) {
		System.out.println(this.getClass().getName() + ".execute()");
		service.saveAll(false);
	}

	@CanExecute
	boolean canExecute(@Optional final MWindow window) {
		if (window == null)
			return false;
		final IEclipseContext context = window.getContext();
		if (context == null)
			return false;
		final EPartService partService = context.get(EPartService.class);
		if (partService == null)
			return false;
		return !partService.getDirtyParts().isEmpty();
	}

}
