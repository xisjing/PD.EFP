package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class IntegerInput extends IntInput {
	private static final long serialVersionUID = 1L;

	public IntegerInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		return Integer.parseInt(this.getValue());
	}

}
