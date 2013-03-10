package e4.rcp.todo.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.swt.widgets.Display;

public class ModelServiceExampleHandler {
	@Execute
	public void execute(final MApplication application,
			final EModelService service, final Display display) {
		System.out.println("Got Model Service: " + (service != null));
		// Alternatively to get the model service
		// via the application
		final EModelService modelService = (EModelService) application
				.getContext().get(EModelService.class.getName());
		// both services are identical
		System.out.println("Got Model Service: " + (service != modelService));
		// Find objects by ID
		this.findObjectsById(application, service);
		// Find objects by type
		this.findObjectsByType(application, service);
		// Find objects by tags
		this.findObjectsByTag(application, service);
		// Get the MWindow and change its size
		this.getWindowAndChangeSize(application, service, display);
	}

	// Example for search by ID
	private void findObjectsById(final MApplication application,
			final EModelService service) {
		final List<MPart> findElements = service.findElements(application,
				"mypart", MPart.class, null);
		System.out.println("Found part(s) : " + findElements.size());
	}

	// Example for search by Type
	private void findObjectsByType(final MApplication application,
			final EModelService service) {
		final List<MPart> parts = service.findElements(application, null,
				MPart.class, null);
		System.out.println("Found parts(s) : " + parts.size());
	}

	// Example for search by Tag
	private void findObjectsByTag(final MApplication application,
			final EModelService service) {
		final List<String> tags = new ArrayList<String>();
		tags.add("justatag");
		final List<MUIElement> elementsWithTags = service.findElements(
				application, null, null, tags);
		System.out.println("Found parts(s) : " + elementsWithTags.size());
	}

	// Example: Get the MWindow and change its size
	private void getWindowAndChangeSize(final MApplication application,
			final EModelService service, final Display display) {
		final List<MWindow> windows = service.findElements(application, null,
				MWindow.class, null);
		if (windows.size() >= 1) {
			final MWindow mWindow = windows.get(0);
			System.out.println("Got the window");
			final int minWindowWidth = mWindow.getWidth() - 100;
			for (int i = mWindow.getWidth(); i >= minWindowWidth; i--) {
				System.out.println("window width = " + mWindow.getWidth());
				while (!display.readAndDispatch()) {
					System.out.println("Set Window width and sleeping");
					mWindow.setWidth(i);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

}