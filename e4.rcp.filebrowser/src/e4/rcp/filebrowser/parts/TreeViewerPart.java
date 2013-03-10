package e4.rcp.filebrowser.parts;

import java.io.File;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class TreeViewerPart {
	
	TreeViewer treeViewer;

	public TreeViewerPart() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		treeViewer.setContentProvider(new ViewContentProvider());
		treeViewer.setLabelProvider(new ViewLabelProvider());
		treeViewer.setInput(File.listRoots());
		Tree tree = treeViewer.getTree();
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}
	
	class ViewContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return (File[]) inputElement;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			File file = (File) parentElement;
			return file.listFiles();
		}

		@Override
		public Object getParent(Object element) {
			return ((File) element).getParentFile();
		}

		@Override
		public boolean hasChildren(Object element) {
			File file = (File) element;
			if (file.isDirectory()) {
				return true;
			}
			return false;
		}

	}

	class ViewLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			File file = (File) element;
			String name = file.getName();
			if (name.length() > 0) {
				return name;
			}
			return file.getPath();
		}

		public Image getImage(Object obj) {
			if (obj instanceof File) {
				File file = (File)obj;
				if (file.isDirectory())
					System.out.println(file.getName() + ": is a Directory");
				else
					System.out.println(file.getName() + ": is a File");
			}
			Bundle bundle = FrameworkUtil.getBundle(this.getClass());
			URL url = FileLocator.find(bundle, new Path("icons/test.gif"), null);
			ImageDescriptor image = ImageDescriptor.createFromURL(url);
			return image.createImage();
		}
	}

}
