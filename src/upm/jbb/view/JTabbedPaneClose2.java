package upm.jbb.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JTabbedPaneClose2 extends JTabbedPane implements ChangeListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JLabel x;

	public JTabbedPaneClose2() {
		super();
		this.x = new JLabel(new ImageIcon("img" + File.separator + "x2.gif"));
		this.x.setToolTipText("Close");
		this.x.addMouseListener(this);
		this.addChangeListener(this);
	}

	@Override
	public void addTab(String title, Component component) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(new JLabel(title + "   "));
		if (this.getTabCount() == 0) {
			this.x.setIcon(new ImageIcon("img" + File.separator + "x2.gif"));
			panel.add(this.x);
		}
		super.addTab(null, component);
		super.setTabComponentAt(this.getTabCount() - 1, panel);
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		JPanel c = null;
		if (this.getSelectedIndex() != -1)
			c = (JPanel) this.getTabComponentAt(this.getSelectedIndex());
		if (c != null) c.add(this.x);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.remove(this.getSelectedComponent());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.x.setIcon(new ImageIcon("img" + File.separator + "x.gif"));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.x.setIcon(new ImageIcon("img" + File.separator + "x2.gif"));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
