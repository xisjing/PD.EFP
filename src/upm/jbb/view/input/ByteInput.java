package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class ByteInput extends IntInput {
	private static final long serialVersionUID = 1L;

	public ByteInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		return Byte.parseByte(this.getValue());
	}

}
