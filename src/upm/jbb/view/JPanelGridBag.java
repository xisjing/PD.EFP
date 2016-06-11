package upm.jbb.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelGridBag extends JPanel {
	private static final long serialVersionUID = 1L;

	private GridBagConstraints cnf;

	public JPanelGridBag() {
		super(new GridBagLayout());
		this.cnf = new GridBagConstraints();
	}

	public void addComponent(String name, Component componente) {
		this.cnf.anchor = GridBagConstraints.SOUTHWEST;
		this.cnf.gridwidth = 1;
		JLabel l = new JLabel(name);
		l.setFocusable(false);
		this.add(l , cnf);
		cnf.gridwidth = GridBagConstraints.REMAINDER;
		this.add(componente, cnf);
	}
	
	public void addComponent(Component componente) {
		this.cnf.anchor = GridBagConstraints.SOUTHWEST;
		cnf.gridwidth = GridBagConstraints.REMAINDER;
		this.add(componente, cnf);
	}

}