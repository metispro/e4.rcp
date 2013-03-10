package e4.rcp.wizard.handlers;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import e4.rcp.wizard.ui.MyWizard;

public class LaunchMyWizard {
	
	@Execute
	public void execute(
			IEclipseContext context,
			@Named(IServiceConstants.ACTIVE_SHELL) Shell shell)
			throws InvocationTargetException, InterruptedException {
		WizardDialog wizardDialog = new WizardDialog(shell,new MyWizard());
		
		if ( wizardDialog.open() == Window.OK) 
			System.out.println("Ok pressed");
		else
			System.out.println("Cancel pressed");
	}

}
