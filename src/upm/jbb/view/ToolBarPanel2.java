package upm.jbb.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ToolBarPanel2 extends JPanel implements ItemCommand {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints cnf;

	public ToolBarPanel2() {
		super(new GridBagLayout());
		this.cnf = new GridBagConstraints();
		this.cnf.anchor = GridBagConstraints.CENTER;
		this.cnf.fill= GridBagConstraints.BOTH;
		this.cnf.gridwidth = 1;
	}
	@Override
	public Component add(Component componente) {
		if((this.getComponents().length+1)%8==0) cnf.gridwidth = GridBagConstraints.REMAINDER;
		this.add(componente, cnf);
		cnf.gridwidth = 1;
		return null;
	}


	@Override
	public String getName() {
		return "show actions";
	}

	@Override
	public void deselected() {
		this.setVisible(false);
	}

	@Override
	public void selected() {
		this.setVisible(true);
	}

}
