package e4.rcp.wizard.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class MyPageTwo extends WizardPage {
	
	private Text text1;
	private Composite container;
	private Label lblThisIsA;
	private Button btnCheckButton;

	public MyPageTwo(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Fake Wizard. " + pageName);
		setControl(text1);
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Say hello to Fred");
		
		text1 = new Text(container, SWT.BORDER);
		text1.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (!text1.getText().isEmpty())
					setPageComplete(true);
			}
		});
		text1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblThisIsA = new Label(container, SWT.NONE);
		lblThisIsA.setText("This is a check");
		
		btnCheckButton = new Button(container, SWT.CHECK);
		btnCheckButton.setSelection(true);
		
		setControl(container);
		setPageComplete(false);
	}
	
	public String getText1() {
		return text1.getText();
	}

}
