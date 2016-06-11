package upm.jbb.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionButton extends JButton implements ActionListener, ItemCommand, Runnable {
    private static final long serialVersionUID = 1L;

    private ActionCommand action;

    public ActionButton(ActionCommand action) {
        super(action.getName());
        this.action = action;
        this.addActionListener(this);
    }

    @Override
    public String getName() {
        return action.getName();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void deselected() {
        this.setVisible(false);
    }

    @Override
    public void selected() {
        this.setVisible(true);
    }
    
    @Override
    public void run() {
        this.action.execute();
    }

}
