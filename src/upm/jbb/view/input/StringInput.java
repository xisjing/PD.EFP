package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class StringInput extends SingleInput {
	private static final long serialVersionUID = 1L;

	public StringInput(InputType model) {
		super(model);
		if (this.getInputType().getValue()==null)this.getInputType().setValue("");
	}

	@Override
	public Object parse() {
		return this.getValue();
	}

}
