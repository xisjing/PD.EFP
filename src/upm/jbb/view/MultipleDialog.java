package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import upm.jbb.view.input.InputPanel;
import upm.jbb.view.input.InputPanelListener;

public class MultipleDialog extends JDialog
		implements
			WindowListener,
			ActionListener,
			InputPanelListener {
	private static final long serialVersionUID = 1L;

	private JButton btnAceptar;
	private JPanelGridBag central;
	private InputPanel[] inputPanel;
	private IOFrame owner;

	public MultipleDialog(IOFrame owner, String title, InputPanel[] inputPanel) {
		super(owner, title, ModalityType.APPLICATION_MODAL);
		this.inputPanel = inputPanel;
		// this.inputPanel.addInputPanelListener(this);
		for (InputPanel input : inputPanel) {
			input.addInputPanelListener(this);
		}
		this.owner = owner;
		this.getContentPane().setLayout(new BorderLayout());
		JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		sur.add(this.btnAceptar);
		central = new JPanelGridBag();
		for (InputPanel input : inputPanel) {
			central.addComponent(input);
		}
		this.getContentPane().add(central, BorderLayout.CENTER);
		this.getContentPane().add(sur, BorderLayout.SOUTH);
		this.addWindowListener(this);
		this.setLocation(owner.newLocationDialog());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		boolean error = false;
		for (InputPanel input : inputPanel) {
			input.save();
			if (input.getInputType().isError()) error = true;
		}
		if (!error) this.windowClosing(null);
		this.pack();
	}

	@Override
	public void windowClosing(WindowEvent event) {
		this.setVisible(false);
		this.owner.removeLocationDialog();
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		this.pack();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		this.pack();
	}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {
		this.pack();
	}

	@Override
	public void accept() {
		for (int i = 0; i < inputPanel.length; i++) {
			if (inputPanel[i].isFocusOwner()) {
				if (i < inputPanel.length - 1) inputPanel[i + 1].requestFocus();
				else this.actionPerformed(null);
				return;
			}
		}
	}

}
