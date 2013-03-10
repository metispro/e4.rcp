package e4.rcp.todo.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class ShowDetailsHandler {

	@Execute
	public void execute(final EPartService service) {
		final MPart part = service.findPart("e4.rcp.todo.part.tododetails");

		part.setVisible(true);

		service.showPart(part, PartState.VISIBLE);
	}

}
