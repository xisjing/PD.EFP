package upm.jbb.view.input;

import upm.jbb.io.InputType;

public class CharacterInput extends SingleInput {
	private static final long serialVersionUID = 1L;

	public CharacterInput(InputType model) {
		super(model);
	}

	@Override
	public Object parse() {
		if (((String) this.getValue()).length() != 1) return new NumberFormatException("La longitud es <> de 1");
		return ((String) this.getValue()).charAt(0);
	}

}
