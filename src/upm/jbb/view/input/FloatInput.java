package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class FloatInput extends DecimalInput {
	private static final long serialVersionUID = 1L;

	public FloatInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		return Float.parseFloat(this.getValue());
	}

}
