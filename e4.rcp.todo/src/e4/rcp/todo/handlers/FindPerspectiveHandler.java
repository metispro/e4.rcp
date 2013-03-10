package e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class FindPerspectiveHandler {
	@Execute
	public void execute(final MApplication application,
			final EModelService service) {
		System.out.println("Called save");
		final MUIElement element = service.find(
				"e4.rcp.todo.partstack.tododetails", application);
		final MPerspective perspective = service.getPerspectiveFor(element);
		System.out.println(perspective);
	}
}
