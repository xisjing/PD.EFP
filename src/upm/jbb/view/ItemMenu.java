package upm.jbb.view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBoxMenuItem;

public class ItemMenu extends JCheckBoxMenuItem implements ItemListener {
	private static final long serialVersionUID = 1L;
	
	private ItemCommand itemCommand;

	
	public ItemMenu(ItemCommand itemCommand) {
		super("Show "+itemCommand.getName(),true);
		this.itemCommand = itemCommand;
		this.addItemListener(this);
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED)this.itemCommand.selected();
		else if (e.getStateChange()==ItemEvent.DESELECTED)this.itemCommand.deselected();
	}

}
