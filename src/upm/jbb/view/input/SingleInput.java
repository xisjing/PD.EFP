package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import upm.jbb.io.InputType;

public abstract class SingleInput extends InputPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField campo;

	public SingleInput(InputType type) {
		super(type);
		this.campo = new JTextField();
		if (type.getValue() != null) this.campo.setText(type.getValue().toString());
		this.campo.setColumns(15);
		this.campo.addKeyListener(this);
		this.campo.setToolTipText(type.getType());
		this.campo.addActionListener(this);
		this.add(campo);
	}

	protected String getValue() {
		return this.campo.getText();
	}

	protected abstract Object parse();

	@Override
	public void save() {
		{
			Object result;
			try {
				result = this.parse();
				if (result != null) {
					this.getInputType().setValue(result);
					this.getInputType().setError("");
				}
			} catch (NumberFormatException e) {
				this.getInputType().setError(
						"Error de conversión a " + this.getInputType().getType() + ": "
								+ this.getValue());
			}
		}
	}

	@Override
	protected void setValue(String value) {
		this.campo.setText(value);
		this.campo.requestFocus();
		this.campo.select(0, this.campo.getText().length());
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		this.accept();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public boolean isFocusOwner() {
		return this.campo.isFocusOwner();
	}
	
	@Override
	public void requestFocus() {
		this.campo.requestFocus();
	}
	
	

}