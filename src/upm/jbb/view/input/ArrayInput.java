package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import upm.jbb.IO;
import upm.jbb.io.Array;
import upm.jbb.io.InputType;

public class ArrayInput extends CompositeInput implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Array array;

	public ArrayInput(InputType type, IO io) {
		super(type, io);
	}

	@Override
	protected String getValue() {
		return Arrays.toString((Object[]) this.getInputType().getValue());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Se crean las collecciones
		if (this.array == null) {
			this.array = new Array(this.getInputType());
			this.getInputType().setValue(array.getArray());
		}

		Object element = this.getIO().read(this.getInputType().arrayType(),
				this.getInputType().arrayType());
		if (element != null) {
			this.array.addElement(element);
			this.getInputType().setValue(array.getArray());
		}

	}

}