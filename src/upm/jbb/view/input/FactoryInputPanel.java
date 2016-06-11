package upm.jbb.view.input;

import upm.jbb.IO;
import upm.jbb.io.InputType;

public class FactoryInputPanel {
	public InputPanel getInputPanel(InputType inputType, IO io) {
		if (inputType.isString()) return new StringInput(inputType);
		else if (inputType.isByte()) return new ByteInput(inputType);
		else if (inputType.isShort()) return new ShortInput(inputType);
		else if (inputType.isInteger()) return new IntegerInput(inputType);
		else if (inputType.isLong()) return new LongInput(inputType);
		else if (inputType.isFloat()) return new FloatInput(inputType);
		else if (inputType.isDouble()) return new DoubleInput(inputType);
		else if (inputType.isBoolean()) return new EnumeratedInput(inputType);
		else if (inputType.isChar()) return new CharacterInput(inputType);
		else if (inputType.isEnum()) return new EnumeratedInput(inputType);
		else if (inputType.isObject()) return new ObjectInput(inputType, io);
		else if (inputType.isSelect()) return new EnumeratedInput(inputType);
		else if (inputType.isCollection()) return new CollectionInput(inputType, io);
		else if (inputType.isArray()) return new ArrayInput(inputType, io);
		else if (inputType.isColor()) return new ColorInput(inputType);
		else if (inputType.isFile()) return new FileInput(inputType, io);
		else return new UserObjectInput(inputType, io);
	}
}
