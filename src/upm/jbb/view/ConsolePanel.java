package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import upm.jbb.io.Console;
import upm.jbb.io.ConsoleListener;

public class ConsolePanel extends JPanel implements ConsoleListener, ItemCommand {
	private static final long serialVersionUID = 1L;
	private Console consola;
	private JTextArea area;

	public ConsolePanel() {
		this.consola = new Console(this);
		this.setLayout(new BorderLayout());
		this.area = new JTextArea(5, 5);
		this.area.setEditable(false);
		this.area.setOpaque(true);
		this.area.setBackground(Color.LIGHT_GRAY);
		this.area.setToolTipText("Consola: System.out.print...");
		JScrollPane scroll = new JScrollPane(this.area);
		this.add(scroll, BorderLayout.CENTER);
	}

	@Override
	public void update(String msg) {
		this.area.setText(msg);
		this.area.setCaretPosition(this.area.getText().length());

	}

	public void clear() {
		this.consola.reset();
	}

	@Override
	public void deselected() {
		this.setVisible(false);
	}

	@Override
	public void selected() {
		this.setVisible(true);
	}

	@Override
	public String getName() {
		return "Console";
	}

}
