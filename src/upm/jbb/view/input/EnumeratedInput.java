package upm.jbb.view.input;

import javax.swing.JComboBox;

import upm.jbb.io.InputType;

/**
 * A partir de un conjunto de objetos, el usuario elige uno.
 */
public class EnumeratedInput extends InputPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> select;

	public EnumeratedInput(InputType type) {
		super(type);
		this.select = new JComboBox<Object>(type.getValues());
		this.select.setToolTipText(type.getType());
		if (type.getValue() != null) this.select.setSelectedItem(type.getValue());
		this.add(this.select);
	}

	@Override
	protected void setValue(String value) {
		this.select.setSelectedItem(value);
	}

	@Override
	public void save() {
		this.getInputType().setValue(this.select.getSelectedItem());
	}
}