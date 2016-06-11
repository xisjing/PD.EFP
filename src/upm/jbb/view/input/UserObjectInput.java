package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import upm.jbb.IO;
import upm.jbb.io.InputType;

public class UserObjectInput extends CompositeInput implements ActionListener {
	private static final long serialVersionUID = 1L;

	public UserObjectInput(InputType type, IO io) {
		super(type, io);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object element = this.getIO().read(this.getInputType().getType(),
				this.getInputType().getType());
		if (element != null) {
			this.getInputType().setValue(element);
		}

	}

}