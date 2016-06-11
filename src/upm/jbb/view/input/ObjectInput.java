package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import upm.jbb.IO;
import upm.jbb.io.InputType;

/**
 * Trabaja con el tipo "Object". Le pide al usuario que determine el tipo
 * concreto para crear una instancia
 * 
 */
public class ObjectInput extends CompositeInput implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField type; // Tipo concreto

	public ObjectInput(InputType type, IO io) {
		super(type,io);
		this.add(new JLabel("Type: "));
		this.type = new JTextField("");
		this.type.setColumns(15);
		this.type.addActionListener(this);
		this.add(this.type);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		InputType type = new InputType(this.type.getText());
		Object element = this.getIO().read(type.getType(), this.getInputType().getType() + " ("
				+ type.getType() + ")");
		if (element != null) {
			this.getInputType().setValue(element);
		}
	}

}