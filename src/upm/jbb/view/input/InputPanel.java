package upm.jbb.view.input;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import upm.jbb.io.Array;
import upm.jbb.io.InputType;
import upm.jbb.io.InputTypeListener;
/**
 * Representa una vista gráfica para introducir entradas, va acompañada de un
 * mensaje y un campo de error Es la vista de la clase InputType
 * 
 * @author jbernal
 * 
 */
public abstract class InputPanel extends JPanel implements InputTypeListener {
	private static final long serialVersionUID = 1L;


	private JLabel error;
	private InputType inputType;
	private InputPanelListener listener;

	public InputPanel(InputType type, InputPanelListener listener) {
		this.inputType = type;
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.error = new JLabel(inputType.getError());
		this.error.setForeground(Color.RED);
		this.add(this.error);
		this.add(new JLabel(type.getMsg() + ":"));
		this.inputType.addListener(this);
		this.listener = listener;
	}

	public InputPanel(InputType type) {
		this(type, null);
	}

	protected abstract void setValue(String value);

	public InputType getInputType() {
		return inputType;
	}

	@Override
	public void update() {
		this.error.setText(inputType.getError());
		if (inputType.getValue() != null) {
			if (inputType.getValue().getClass().isArray()) {
				Array array = new Array(inputType.getValue());
				this.setValue(array.toString());
			} else this.setValue(inputType.getValue().toString());
		} else this.setValue("null");
	}

	public abstract void save();

	public void addInputPanelListener(InputPanelListener listener) {
		this.listener = listener;
	}

	protected void accept() {
		if (this.listener != null) this.listener.accept();
	}

}