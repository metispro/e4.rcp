package e4.rcp.todo.handlers;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SwitchPerspectiveHandler {
	@Execute
	public void execute(final MApplication app,
			final EModelService modelService, final EPartService partService) {
		final List<MPerspective> list = modelService.findElements(app,
				"e4.rcp.todo.perspective", MPerspective.class, null);
		// Alternatively direct access via the ID
		// Result not used
		final MPerspective element = (MPerspective) modelService.find(
				"e4.rcp.todo.perspective", app);
		// Now switch perspective
		partService.switchPerspective(list.get(0));
	}
}
