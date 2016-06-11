package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import upm.jbb.IO;
import upm.jbb.io.InputType;

public class FileInput extends CompositeInput {
	private static final long serialVersionUID = 1L;

	private JFileChooser chooser;

	public FileInput(InputType type, IO io) {
		super(type, io);
		this.chooser=io.getChooser();
		if (this.getInputType().getValues() != null) {
			String descripcion = "";
			String[] exts = new String[this.getInputType().getValues().length];
			for (int i = 0; i < exts.length; i++) {
				exts[i] = (String) this.getInputType().getValues()[i];
				descripcion += exts[i] + " ";
			}
			descripcion += "files";
			FileNameExtensionFilter filter = new FileNameExtensionFilter(descripcion, exts);
			chooser.setFileFilter(filter);
		}
		chooser.setToolTipText(this.getInputType().getMsg());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (this.getInputType().getMsg().indexOf("Save") != -1) chooser.showSaveDialog(this.getIO()
				.getIoFrame());
		else chooser.showOpenDialog(this.getIO().getIoFrame());
		if (chooser.getSelectedFile() == null) return;
		this.getInputType().setValue(new File(chooser.getSelectedFile().getAbsolutePath()));
	}

}
