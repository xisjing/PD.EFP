package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutPanel extends JPanel implements ItemCommand{
	private static final long serialVersionUID = 1L;

	private JTextArea txtOut;
	private IOFrame ioFrame;

	public OutPanel(IOFrame ioFrame) {
		super(new BorderLayout());
		this.ioFrame = ioFrame;
		this.txtOut = new JTextArea("");
		this.txtOut.setToolTipText("Salida: IO.out.print(...), IO.out.println(...)");
		this.txtOut.setMargin(new Insets(3, 5, 3, 3));
		JScrollPane scroll = new  JScrollPane(this.txtOut);
		this.add(scroll,BorderLayout.CENTER);
		
	}

	public void addMsg(String msg){
		this.txtOut.append(msg);
		this.txtOut.setCaretPosition(this.txtOut.getText().length());
	}
	
	public void clear(){
		txtOut.setText("");
	}
	
	public String getText(){
		return txtOut.getText();
	}

	@Override
	public void deselected() {
		this.ioFrame.viewOut(false);
	}

	@Override
	public void selected() {
		this.ioFrame.viewOut(true);
	}
	
	public String getName(){
		return "Out";
	}
}
