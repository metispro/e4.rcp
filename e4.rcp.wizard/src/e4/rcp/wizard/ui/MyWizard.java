package e4.rcp.wizard.ui;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {
	
	protected MyPageOne pageOne;
	protected MyPageTwo pageTwo;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		pageOne = new MyPageOne("First Page");
		pageTwo = new MyPageTwo("Second Page");
		super.addPage(pageOne);
		super.addPage(pageTwo);
	}

	@Override
	public boolean performFinish() {
		System.out.println(pageOne.getText1());
		System.out.println(pageTwo.getText1());
		return true;
	}

}
