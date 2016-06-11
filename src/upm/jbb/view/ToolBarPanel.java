package upm.jbb.view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ToolBarPanel extends JPanel implements ItemCommand {
	private static final long serialVersionUID = 1L;

	public ToolBarPanel() {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
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
