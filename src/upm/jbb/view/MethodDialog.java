package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class MethodDialog extends JDialog implements WindowListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton btnCerrar;
	private JCheckBox setters;

	public MethodDialog(IOFrame owner, String title, Component centro, boolean isConstructor) {
		super(owner, title, ModalityType.APPLICATION_MODAL);
		this.getContentPane().setLayout(new BorderLayout());
		this.setters = new JCheckBox(
				"Utilizar los métodos setters para terminar de inicializar la instancia", false);
		if (isConstructor) this.add(this.setters, BorderLayout.NORTH);
		this.add(centro, BorderLayout.CENTER);
		JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		this.btnCerrar = new JButton("Cerrar");
		this.btnCerrar.addActionListener(this);
		sur.add(this.btnCerrar);
		this.getContentPane().add(sur, BorderLayout.SOUTH);
		this.addWindowListener(this);
		this.setLocation(owner.newLocationDialog());
		this.setSize(650,300);
	}

	public MethodDialog(IOFrame owner, String title, Component centro) {
		this(owner, title, centro, false);
	}

	public boolean isSetters() {
		return this.setters.isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent event) {
		this.setVisible(false);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		//this.pack();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		//this.pack();
	}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {
		//this.pack();
	}

}
