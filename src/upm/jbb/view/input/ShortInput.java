package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class ShortInput extends IntInput {
	private static final long serialVersionUID = 1L;

	public ShortInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		return Short.parseShort(this.getValue());
	}

}
