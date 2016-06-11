package upm.jbb.view;

import javax.swing.JTextArea;

public class StateBar extends JTextArea implements ItemCommand {
	private static final long serialVersionUID = 1L;

	public StateBar(String text) {
		super(text);
	}

	@Override
	public String getName() {
		return "State Bar";
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
