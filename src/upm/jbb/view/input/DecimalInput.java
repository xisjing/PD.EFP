package upm.jbb.view.input;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import upm.jbb.io.InputType;

public abstract class DecimalInput extends SingleInput {
	private static final long serialVersionUID = 1L;

	public DecimalInput(InputType model) {
		super(model);
		if (this.getInputType().getValue()==null)this.getInputType().setValue(0.0);
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		char car = ke.getKeyChar();
		JTextField texto = (JTextField) ke.getSource();
		if ((car < '0' || car > '9')
				&& (car != KeyEvent.VK_DELETE)
				&& (car != KeyEvent.VK_BACK_SPACE)
				&& (car != KeyEvent.CHAR_UNDEFINED)
				&& (car != KeyEvent.VK_ENTER)
				&& (car != KeyEvent.VK_PERIOD || texto.getText().indexOf(".") != -1)
				&& (car != KeyEvent.VK_E || texto.getText().indexOf("E") != -1)
				&& (car != KeyEvent.VK_MINUS || texto.getCaretPosition() != 0)
				&& (car != KeyEvent.VK_MINUS || (texto.getText().indexOf("E") + 1) != texto
						.getCaretPosition())) {
			ke.consume();
		}
	}

}
