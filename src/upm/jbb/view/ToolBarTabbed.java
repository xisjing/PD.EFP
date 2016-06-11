package upm.jbb.view;


import javax.swing.JTabbedPane;

public class ToolBarTabbed extends JTabbedPane implements ItemCommand{
	private static final long serialVersionUID = 1L;
	
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
