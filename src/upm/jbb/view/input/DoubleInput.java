package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class DoubleInput extends DecimalInput {
	private static final long serialVersionUID = 1L;

	public DoubleInput(InputType model) {
		super(model);
	}
	@Override
	public Object parse() {
		return Double.parseDouble(this.getValue());
	}

}
