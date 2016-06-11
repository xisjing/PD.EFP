package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import upm.jbb.IO;
import upm.jbb.io.InputType;

public abstract class CompositeInput extends InputPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	protected JTextField value; // valor del objeto
	private JButton addObject;
	private IO io;

	public CompositeInput(InputType type, IO io) {
		super(type);
		String strValue;
		this.io = io;
		if (type.getValue() == null) strValue = "null";
		else strValue = this.getValue();
		this.value = new JTextField(strValue);
		this.value.setColumns(30);
		this.value.setToolTipText(type.getType());
		this.value.setEditable(false);
		this.addObject = new JButton("Add (" + type.getType() + ")");
		this.addObject.addActionListener(this);
		this.addObject.addKeyListener(this);
		this.add(value);
		this.add(addObject);
	}

	@Override
	protected void setValue(String value) {
		this.value.setText(value);
	}

	protected String getValue() {
		return this.getInputType().getValue().toString();
	}

	@Override
	public void save() {}

	@Override
	public void actionPerformed(ActionEvent event) {}

	protected IO getIO() {
		return this.io;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		char car = ke.getKeyChar();
		if (car == KeyEvent.VK_ENTER) this.actionPerformed(null);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}