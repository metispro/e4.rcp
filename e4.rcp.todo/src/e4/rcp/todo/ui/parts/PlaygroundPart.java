/**
 * 
 */
package e4.rcp.todo.ui.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * @author TF016851
 * 
 */
public class PlaygroundPart {

	Label label;
	private Text text;

	@PostConstruct
	public void createControls(Composite parent) {
		System.out.println(this.getClass().getName() + ".createCrontols(Composite parent)"); //$NON-NLS-1$
		parent.setLayout(new GridLayout(2, false));
		label = new Label(parent, SWT.NONE);
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setText(Messages.getString("PlaygroundPart.1", "New Button")); //$NON-NLS-1$
		new Label(parent, SWT.NONE);
		
		Button btnNewButton_1 = new Button(parent, SWT.NONE);
		btnNewButton_1.setText(Messages.getString("PlaygroundPart.2", "New Button")); //$NON-NLS-1$
		new Label(parent, SWT.NONE);
		
		Button btnNewButton_2 = new Button(parent, SWT.NONE);
		btnNewButton_2.setText(Messages.getString("PlaygroundPart.3", "New Button")); //$NON-NLS-1$
		
		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText(Messages.getString("PlaygroundPart.4", "New Label")); //$NON-NLS-1$
		
		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@PreDestroy
	public void dispose() {

	}

	@Focus
	private void setFocus() {
		label.setFocus();
	}
}
