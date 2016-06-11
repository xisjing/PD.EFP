package upm.jbb.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import upm.jbb.IO;
import upm.jbb.reflect.AbstractMethod;
import upm.jbb.reflect.MethodBuilder;

public class IOFrame extends JFrame implements ActionListener, WindowListener {
	private static final String VERSION = "© Jesús Bernal Bermúdez (versión: 5.5, noviembre-2014)";

	private static final long serialVersionUID = 1L;
	private static int locationFrame = 0;

	private synchronized Point newLocationFrame() {
		IOFrame.locationFrame += 40;
		IOFrame.locationFrame %= 400;
		return new Point(IOFrame.locationFrame, IOFrame.locationFrame);
	}

	private int locationDialog = 40;

	public synchronized Point newLocationDialog() {
		this.locationDialog += 40;
		this.locationDialog %= 400;
		Point p = new Point(this.locationDialog, this.locationDialog);
		p.translate(this.getLocation().x, this.getLocation().y);
		return p;
	}

	public synchronized void removeLocationDialog() {
		this.locationDialog -= 40;
		this.locationDialog %= 400;
	}

	private Container contenedor;
	private ToolBarPanel pnlToolBar;
	//private ToolBarTabbed pnlToolBarTabbed;
	private OutPanel pnlOut;
	private JPanel pnlSur;
	private ConsolePanel pnlConsole;
	private StateBar txtStateBar;
	private JMenu mView;
	private ItemMenu mActionsView;
	private JMenu mActions;

	private JTabbedPaneClose pestania;

	private IO io;

	public IOFrame(String tittle, IO io) {
		super(tittle);
		this.io = io;
		this.addWindowListener(this);
		this.contenedor = this.getContentPane();

		// Barra de herramientas
		this.pnlToolBar = new ToolBarPanel();
		//this.pnlToolBarTabbed = new ToolBarTabbed();

		// Cuerpo =======================================================

		this.contenedor.setLayout(new BorderLayout());
		this.pestania = new JTabbedPaneClose();

		// Text IO.out
		this.pnlOut = new OutPanel(this);
		this.pestania.add("Out", this.pnlOut);
		this.contenedor.add(pestania, BorderLayout.CENTER);

		// Barra de herramientas
		this.contenedor.add(this.pnlToolBar, BorderLayout.NORTH);
		//this.contenedor.add(this.pnlToolBarTabbed, BorderLayout.NORTH);
		// Consola y Barra de estado
		this.pnlSur = new JPanel(new BorderLayout());
		pnlConsole = new ConsolePanel();
		this.pnlSur.add(pnlConsole, BorderLayout.CENTER);
		this.txtStateBar = new StateBar(VERSION);
		this.txtStateBar.setToolTipText("Barra de estado: IO.out.setStatusBar(\"Valor\")");
		this.txtStateBar.setOpaque(true);
		this.txtStateBar.setBackground(Color.GRAY);
		this.txtStateBar.setEditable(false);
		this.pnlSur.add(this.txtStateBar, BorderLayout.SOUTH);
		this.contenedor.add(pnlSur, BorderLayout.SOUTH);

		// Menus ========================================================
		JMenuBar mBar = new JMenuBar();

		JMenu mFile = new JMenu("File");
		mBar.add(mFile);
		JMenuItem mNew = new JMenuItem("Clear out", KeyEvent.VK_O);
		mNew.addActionListener(this);
		mFile.add(mNew);
		JMenuItem mClear = new JMenuItem("Clear console", KeyEvent.VK_S);
		mClear.addActionListener(this);
		mFile.add(mClear);
		mFile.add(new JSeparator());
		JMenuItem mExit = new JMenuItem("Exit", KeyEvent.VK_E);
		mExit.addActionListener(this);
		mFile.add(mExit);

		JMenu mClass = new JMenu("Class");
		mBar.add(mClass);
		JMenuItem mCreateActions = new JMenuItem("Add View", KeyEvent.VK_C);
		mCreateActions.addActionListener(this);
		mClass.add(mCreateActions);
		JMenuItem mCreateMethods = new JMenuItem("Add View Panel", KeyEvent.VK_M);
		mCreateMethods.addActionListener(this);
		mClass.add(mCreateMethods);

		this.mView = new JMenu("View");
		mBar.add(mView);

		mActionsView = new ItemMenu(pnlToolBar);
		//mActionsView = new ItemMenu(pnlToolBarTabbed);
		mActionsView.setSelected(false);
		mActionsView.setEnabled(false);
		this.mView.add(mActionsView);

		JMenuItem mOut = new JMenuItem("Show Out");
		mOut.addActionListener(this);
		this.mView.add(mOut);

		ItemMenu mConsole = new ItemMenu(pnlConsole);
		this.mView.add(mConsole);

		ItemMenu mSta = new ItemMenu(txtStateBar);
		mView.add(mSta);

		mActions = new JMenu("Actions");
		mBar.add(mActions);

		JMenu mHelp = new JMenu("Help");
		mBar.add(mHelp);
		JMenuItem mContents = new JMenuItem("Help Contents", KeyEvent.VK_H);
		mContents.addActionListener(this);
		mHelp.add(mContents);
		JMenuItem mAbout = new JMenuItem("About IO", KeyEvent.VK_A);
		mAbout.addActionListener(this);
		mHelp.add(mAbout);

		this.setJMenuBar(mBar);

		// ------------------------------------------------------------------------
		this.setLocation(this.newLocationFrame());
		this.setSize(750, 400);
		this.setVisible(true);
	}

	public IO getIo() {
		return io;
	}

	public void clear() {
		this.pnlOut.clear();
	}

	public void setStatusBar(String msg) {
		this.txtStateBar.setText(msg);
	}

	public void addText(String msg) {
		this.pnlOut.addMsg(msg);
	}

	public void addAction(String name, List<AbstractMethod> list, boolean createBar) {
		//ToolBarPanel pnlToolBar= new ToolBarPanel();
	    JToolBar tool= new JToolBar(name);
		JMenu menuView = new JMenu(name);
		this.mView.add(menuView);
		JMenu menuActions = new JMenu(name);
		this.mActions.add(menuActions);
		
		
		
		for (ActionCommand action : list) {
			ActionButton ab = new ActionButton(action);
			ItemMenu im = new ItemMenu(ab);
            //this.pnlToolBar.add(ab);
			tool.add(ab); //--
			menuView.add(im);
			menuActions.add(new ActionMenu(action));
			if (!createBar) im.setSelected(false);
		}
		this.pnlToolBar.add(tool);//--
		this.mActionsView.setSelected(true);
		this.mActionsView.setEnabled(true);
		this.setVisible(true);
		//this.pnlToolBarTabbed.add(name,pnlToolBar);
	}
	
    public void setEnablebButton(String name, boolean b){
        Component[] components = this.pnlToolBar.getComponents();
        for (Component component : components) {
            Component[] buttons = ((JToolBar)component).getComponents();
            for (Component button : buttons) {
                if(button.getName().equals(name)) {
                    button.setEnabled(b);
                }
            }
        }
    }
    
	public void addViewPanel(Object modelo) {
		MultipleMethodInputTab mmi = new MultipleMethodInputTab(new MethodBuilder(modelo).create(), this.io);
		this.pestania.addTab(modelo.getClass().getName(), mmi);
	}

	public void viewOut(boolean view) {
		if (view) {
		    pestania.remove(this.pnlOut);
		    pestania.add(this.pnlOut);
		} else
		    pestania.remove(this.pnlOut);
	}

	// Manejadores
	// ==============================================================
	@Override
	public void actionPerformed(ActionEvent ae) {
		// ------ New----------------------------------
		if (ae.getActionCommand().equals("Clear out"))
			this.pnlOut.clear();
		// ------ Print ----------------------------------
		else if (ae.getActionCommand().equals("Clear console")) {
			this.pnlConsole.clear();
		} else if (ae.getActionCommand().equals("Print...")) {
			// Cogemos el servicio de impresi�n por defecto (impresora por
			// defecto)
			PrintService service = PrintServiceLookup.lookupDefaultPrintService();
			// Le decimos el tipo de datos que vamos a enviar a la impresora
			// Tipo: bytes Subtipo: autodetectado
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			// Creamos un trabajo de impresi�n
			DocPrintJob pj = service.createPrintJob();
			// Nuestro trabajo de impresi�n env�a una cadena de texto
			String ss = this.pnlOut.getText();
			byte[] bytes;
			// Transformamos el texto a bytes que es lo que soporta la impresora
			bytes = ss.getBytes();
			// Creamos un documento (Como si fuese una hoja de Word para
			// imprimir)
			Doc doc = new SimpleDoc(bytes, flavor, null);

			// Obligado coger la excepci�n PrintException
			try {
				// Mandamos a impremir el documento
				pj.print(doc, null);
			} catch (PrintException e) {
				System.out.println("Error al imprimir: " + e.getMessage());
			}
			// ------ Exit------------------------------------------------------
		} else if (ae.getActionCommand().equals("Exit")) {
			this.dispose();
		} else if (ae.getActionCommand().equals("Show Out")) {
			this.viewOut(true);
		} else if (ae.getActionCommand().equals("Help Contents")) {
			new HelpDialog(this, VERSION);
		} else if (ae.getActionCommand().equals("About IO")) {
			JOptionPane.showMessageDialog(this, VERSION, "Acerca De", JOptionPane.INFORMATION_MESSAGE);
			// ------ Open File...------------------------------------
		} else if (ae.getActionCommand().equals("Add Controler")) {
			io.addview(this.io.read("Object", "Actions"));
		} else if (ae.getActionCommand().equals("Add Model")) {
			io.addViewPanel(this.io.read("Object", "Model"));
		}
	}

	@Override
	public void windowClosing(WindowEvent we) {
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent we) {}

	@Override
	public void windowClosed(WindowEvent we) {}

	@Override
	public void windowDeactivated(WindowEvent we) {}

	@Override
	public void windowDeiconified(WindowEvent we) {}

	@Override
	public void windowIconified(WindowEvent we) {}

	@Override
	public void windowOpened(WindowEvent we) {}

}