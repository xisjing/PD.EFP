package upm.jbb.view.input;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;

import upm.jbb.io.InputType;

public class ColorInput extends CompositeInput {
	private static final long serialVersionUID = 1L;

	public ColorInput(InputType type) {
		super(type, null);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Color color = (Color) this.getInputType().getValue();
		color = (JColorChooser.showDialog(null, "Elige Color", color));
		if (color == null) return;
		this.getInputType().setValue(color);
	}

	@Override
	protected void setValue(String value) {
		Color c2, c = (Color) this.getInputType().getValue();
		this.value.setText(value);
		if (c == null) return;
		if (c.getRed() + c.getGreen() + c.getBlue() > 255) c2 = Color.BLACK;
		else c2 = Color.WHITE;
		// c2 = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 -
		// c.getBlue());
		this.value.setBackground(c);
		this.value.setForeground(c2);
	}

}
