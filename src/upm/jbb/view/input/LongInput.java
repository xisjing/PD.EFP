package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class LongInput extends IntInput {
	private static final long serialVersionUID = 1L;

	public LongInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		return Long.parseLong(this.getValue());
	}

}
