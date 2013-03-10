package e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class HideDeatilsHandler {

	@Execute
	public void execute(final EPartService service) {
		final MPart part = service.findPart("e4.rcp.todo.part.tododetails");

		service.hidePart(part);
	}

}
