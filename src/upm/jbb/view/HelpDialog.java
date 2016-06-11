package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog implements WindowListener,
		ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnAceptar;

	public HelpDialog(IOFrame owner, String version) {
		super(owner, "Help Content", ModalityType.APPLICATION_MODAL);
		String ayuda = "";
		ayuda += "--Para escribir un texto: IO.getIO().print(...) o IO.getIO().println(...), acepta todos los tipos \n";
		ayuda += "--Las escrituras del tipo: System.out.print(...), se sacan por la consola de la clase IO\n";
		ayuda += "--Para escribir un texto con nivel de LOG: IO.getIO().print(..., Log.INFO) o IO.getIO().println(...,Log.ERROR) \n";
		ayuda += "     Sólo se imprime si el nivel especificado es inferior o igual al actual. Por defecto, son Log.INFO \n";
		ayuda += "     Para cambiar el nivel de LOG actual: IO.getIO().setLog(Log.WARNING): Log.DEBUG, Log.ERROR, Log.WARNING, Log.INFO \n";
		ayuda += "--Para borrar la salida: IO.getIO().clear() \n";
		ayuda += "--Para leer: IO.getIO().read(\"Clase\",\"mensaje\") IO.getIO().read(MiClase.class,\"mensaje\") IO.getIO().read(Wrapper,\"mensaje\")\n";
		ayuda += "     Acepta cualquier nombre de Clase, incluido primitivos, colecciones, arrays, enumerados...\n";
		ayuda += "     No soporta ni arrays multiples, ni combinaciones de coleciones y arrays juntos en un mismo tipo\n";
		ayuda += "     Se deben poner los nombres de paquetes, ej. java.util.Set<String>, mio.Clase (java.lang es opcional)\n";
		ayuda += "     Si se cancela la lectura, devuelve el valor por defecto\n";
		ayuda += "     Ejemplos: IO.getIO().read(\"String[]\",\"\"); IO.getIO().read(\"uno.Clase\",\"\"); IO.getIO().read(\"java.util.Set<<List<String>>\",\"\"); \n";
		ayuda += "     Ejemplos: IO.getIO().read(Clase.class,\"Un clase cualquiera\"); IO.getIO().read(Clase[].class,\"Un array\"); IO.getIO().read(java.awt.Color.RED,\"Color\"); IO.getIO().read(5,\"Valor\"); \n";
		ayuda += "--Para modificar un objeto: IO.getIO().setters(objeto,\"mensaje\") o IO.getIO().setters(objeto)\n";
		ayuda += "--Para leer un String: IO.getIO().readString(\"mensaje\") o IO.getIO().readString()\n";
		ayuda += "     Equivalente a IO.getIO().read(\"String\",\"mensaje\")\n";
		ayuda += "--Para leer un Color: IO.getIO().read(\"java.awt.Color\",\"mensaje\") o IO.getIO().read(\"java.awt.Color\")\n";
		ayuda += "--Para leer un fichero: IO.getIO().readOpenFile(\"mensaje\",String[] extensiones) o IO.getIO().readOpenFile(\"mensaje\") o IO.getIO().readOpenFile()\n";
		ayuda += "     Equivalente a IO.getIO().read(\"java.io.File\",\"Open mensaje\")\n";
		ayuda += "--Para escribir un fichero: IO.getIO().readSaveFile(\"mensaje\",String[] extensiones) o IO.getIO().readSaveFile(\"mensaje\") o IO.getIO().readSaveFile()\n";
		ayuda += "     Equivalente a IO.getIO().read(\"java.io.File\",\"Save mensaje\")\n";
		ayuda += "--Se puede establecer el path: IO.getIO().setPath(String path)\n";
		ayuda += "--Para leer un Formulario de campos (array de String con los tipos, array de class o array de objetos) \n";
		ayuda += "     Ejemplos: String[] msgs={\"Nombre\",\"Edad\",\"Clase\"}; \n";
		ayuda += "     Ejemplos: String[] campos={\"String\",\"Integer\",\"mio.Clase\"}; IO.getIO().readForm(campos, msgs) \n";
		ayuda += "     Ejemplos: Class<?>[] campos={String.class,Integer.class,mio.Clase.class}; IO.getIO().readForm(campos) \n";
		ayuda += "     Ejemplos: Object[] campos={\"Ini\",23,new mio.Clase()}; IO.getIO().readForm(campos,msgs) \n";
		ayuda += "--Para leer un int: IO.getIO().readInt(\"mensaje\") o IO.getIO().readInt() \n";
		ayuda += "     Equivalente a IO.getIO().read(\"int\",\"mensaje\")\n";
		ayuda += "--Para leer un double: IO.getIO().readDouble(\"mensaje\") o IO.getIO().readDouble() \n";
		ayuda += "     Equivalente a IO.getIO().read(\"double\",\"mensaje\")\n";
		ayuda += "--Para elegir un objeto de una lista: IO.getIO().select(array[],\"mensaje\") o IO.getIO().select(array[]) \n";
		ayuda += "     Para leer un valor entero entre 0 y un máximo:IO.getIO().select(max,\"mensaje\")\n";
		ayuda += "     Para leer un valor entero entre un mínimo y un máximo:IO.getIO().select(min,max,\"mensaje\")\n";
		ayuda += "--Para escribir en la barra de estado: IO.getIO().setStatusBar(\"mensaje\")\n\r";
		ayuda += "--Para crear acciones: \n\r";
		ayuda += "     Crear una clase que realiza la función de controlador\n\r";
		ayuda += "     Añadir métodos de tipo void y sin parámetros: public void miAccion(){...}\n\r";
		ayuda += "     Llamar al método: IO.getIO().addView(Object view) o IO.getIO().addView(Object view, boolean viewButton);\n\r";
		ayuda += "     Tambien se permite realizar desde el menu Class\n\r";
		ayuda += "--Para añadir un view como panel independiente: \n\r";
		ayuda += "     Crear una clase que realiza la función de modelo\n\r";
		ayuda += "     Llamar al método: IO.getIO().addViewPanel(Object view);\n\r";
		ayuda += "     Se permite el manejo de varios views\n\r";
		ayuda += "     Tambien se puede realizar desde el menu Class\n\r";
        ayuda += "--Para habilitar/deshabilitar un botón: IO.getIO().setEnabledButton(\"nombre\",boolean b)\n\r";
		ayuda += "--Se permiten varias instancias: IO io = new IO(String titulo); io.read(), io.println(), io.setStatusBar()...\n\r";

		this.getContentPane().setLayout(new BorderLayout());
		JPanel sur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.addActionListener(this);
		sur.add(new JLabel(version+"     "));
		sur.add(this.btnAceptar);
		JTextArea txtAyuda = new JTextArea(ayuda, 25, 80);
		txtAyuda.setEditable(false);
		JScrollPane txtScroll = new JScrollPane(txtAyuda);
		this.getContentPane().add(txtScroll, BorderLayout.CENTER);
		this.getContentPane().add(sur, BorderLayout.SOUTH);
		this.addWindowListener(this);
		this.setLocation(owner.newLocationDialog());
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		this.windowClosing(null);

	}

	@Override
	public void windowClosing(WindowEvent event) {
		this.setVisible(false);
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		this.pack();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		this.pack();
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		this.pack();
	}

}
