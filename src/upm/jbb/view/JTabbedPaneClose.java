package upm.jbb.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTabbedPane;

public class JTabbedPaneClose extends JTabbedPane implements MouseListener {
	private static final long serialVersionUID = 1L;

	public JTabbedPaneClose() {
		super();
		this.setToolTipText("Double click to close");
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount()==2)this.remove(this.getSelectedComponent());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
