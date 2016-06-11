package upm.jbb.view.input;

import upm.jbb.IO;

public class PruebaFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] exts={"pdf"};
		IO.getIO().println(IO.getIO().readOpenFile("doc",exts));
		IO.getIO().println(IO.getIO().readSaveFile());
	}

}
