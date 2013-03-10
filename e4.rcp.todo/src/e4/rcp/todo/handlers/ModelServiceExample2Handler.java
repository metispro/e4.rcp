package e4.rcp.todo.handlers;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class ModelServiceExample2Handler {

	@Execute
	public void execute(final EModelService service, final MWindow window) {
		final MPartSashContainer find = (MPartSashContainer) service.find(
				"e4.rcp.todo.partsashcontainer.details", window);
		final List<MPartSashContainerElement> list = find.getChildren();
		int i = 0;
		// Make the first part in the container larger
		for (final MPartSashContainerElement element : list) {
			element.setContainerData("80");
			if (i > 0) {
				element.setContainerData("20");
			}
			i++;
		}
	}

}
