package upm.jbb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ActionMenu extends JMenuItem implements  ActionListener, Runnable{
	private static final long serialVersionUID = 1L;
	
	private ActionCommand action;

	public ActionMenu(ActionCommand action) {
		super(action.getName());
		this.action = action;
		this.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		Thread t = new Thread(this);
		t.start();
	}


	@Override
	public void run() {
		this.action.execute();
	}


}
