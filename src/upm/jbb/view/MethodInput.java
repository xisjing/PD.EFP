package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import upm.jbb.IO;
import upm.jbb.io.Array;
import upm.jbb.io.InputType;
import upm.jbb.reflect.AbstractMethod;

import upm.jbb.view.input.FactoryInputPanel;
import upm.jbb.view.input.InputPanel;
import upm.jbb.view.input.InputPanelListener;

public class MethodInput extends JPanel implements ActionListener, InputPanelListener {
	private static final long serialVersionUID = 1L;

	private AbstractMethod metodo;
	private JPanelGridBag pnlCentro;
	private JButton btnInvoke;
	private JLabel result;
	private List<InputPanel> inputs;

	public MethodInput(AbstractMethod metodo, IO io) {
		this.metodo = metodo;
		this.setLayout(new BorderLayout());

		pnlCentro = new JPanelGridBag();

		InputType inputType;
		this.inputs = new ArrayList<InputPanel>();
		for (int i = 0; i < metodo.getParams().size(); i++) {
			inputType = metodo.getParams().get(i);
			InputPanel inputPanel = new FactoryInputPanel().getInputPanel(inputType, io);
			inputPanel.addInputPanelListener(this);
			// pnlCentro.add(inputPanel);
			pnlCentro.addComponent("", inputPanel);
			inputs.add(inputPanel);
			inputType.addListener(inputPanel);
			inputPanel.update();
		}

		JScrollPane pnlScCtr = new JScrollPane(this.pnlCentro,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(pnlScCtr, BorderLayout.CENTER);

		JPanel sur = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.btnInvoke = new JButton("Ejecutar");
		this.btnInvoke.addActionListener(this);
		sur.add(this.btnInvoke);
		this.result = new JLabel("");
		sur.add(this.result);
		this.add(sur, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean errors = false;
		for (InputPanel in : this.inputs) {
			in.save();
			if (in.getInputType().isError()) errors = true;
		}
		if (!errors) {
			metodo.execute();
			if (metodo.getError().length() > 0) {
				this.result.setText(metodo.getError());
				this.result.setForeground(Color.RED);
			} else {
				this.result.setForeground(Color.BLACK);
				String res = "return: (" + this.metodo.getReturnType() + ") ";
				Object obj = this.metodo.getReturnValue();
				if (obj == null) {
					if (!this.metodo.getReturnType().equals("void")) res = res + "null";
				} else if (obj.getClass().isArray()) {
					Array array = new Array(obj);
					res = res + array.toString();
				} else res = res + obj.toString();
				this.result.setText(res);

			}
		}

	}

	@Override
	public void accept() {
		Component[] c = pnlCentro.getComponents();
		for (int i = 0; i < c.length; i++) {
			if (c[i].isFocusOwner()) {
				for(i++;i<c.length;i++)
					//Se debe establecer las etiquetas a: setFocusable(false)
					if (c[i].isFocusable()) {
						c[i].requestFocus();
						return;
					}
				this.actionPerformed(null);
			}
		}
	}

}