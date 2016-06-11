package upm.jbb;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

import upm.jbb.io.Array;
import upm.jbb.io.InputType;
import upm.jbb.io.Log;
import upm.jbb.reflect.ActionBuilder;
import upm.jbb.reflect.CollectionOfAbstracMethod;
import upm.jbb.reflect.ConstructorBuilder;
import upm.jbb.reflect.SettersBuilder;
import upm.jbb.view.MethodDialog;
import upm.jbb.view.IOFrame;
import upm.jbb.view.MultipleDialog;
import upm.jbb.view.MultipleMethodInput;
import upm.jbb.view.MultipleMethodInputTab;
import upm.jbb.view.SingleDialog;
import upm.jbb.view.input.FactoryInputPanel;
import upm.jbb.view.input.InputPanel;

public class IO {
    private static final Map<String, IO> ios = new HashMap<>();

    private IOFrame ioFrame;

    private Log log, printLog;

    private JFileChooser chooser;

    private IO(String tittle) {
        this.ioFrame = new IOFrame(tittle, this);
        this.log = this.printLog = Log.INFO;
        this.chooser = new JFileChooser();
    }

    public static IO getIO(String key) {
        if (!ios.containsKey(key)) {
            ios.put(key, new IO(key));
        }
        return ios.get(key);
    }

    public static IO getIO() {
        return IO.getIO("Input/Output");
    }

    public IOFrame getIoFrame() {
        return ioFrame;
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    // ====================== Lecturas======================
    /**
     * Lee una clase
     * 
     * @param clase Nombre de la clase que se quiere leer
     * @param msg Mensaje asociado
     * @return Instancia de la clase leida, o null si existen errores o se
     *         cancela
     */
    public Object read(String clase, String msg) {
        InputType type = new InputType(clase, msg);
        return this.readType(type);
    }

    public Object read(Class<?> clase, String msg) {
        return this.read(clase.getName(), msg);
    }

    /**
     * @param obj Objeto que se quiere leer, se utiliza como valor inicial. Sólo
     *            funciona con los Wrappers
     * @param msg
     * @return
     */
    public Object read(Object obj, String msg) {
        InputType type = new InputType(obj.getClass().getName(), msg);
        type.setValue(obj);
        return this.readType(type);
    }

    private Object readType(InputType type) {
        if (type.isSingle() || type.isCollection() || type.isArray() || type.isObject()) {
            new SingleDialog(this.ioFrame, this.ioFrame.getTitle(),
                    new FactoryInputPanel().getInputPanel(type, this)).setVisible(true);
            return type.getValue();
        } else {
            ConstructorBuilder constructorBuilder = new ConstructorBuilder(type.getType());
            CollectionOfAbstracMethod constructores = constructorBuilder.create();
            if (constructores == null)
                return null;
            if (constructores.getList().size() == 0) {
                System.out.println("WARNING (IO): Clase sin constructores p�blicos: "
                        + type.getType());
                return null;
            }
            MultipleMethodInputTab mmi = new MultipleMethodInputTab(constructores, this);
            MethodDialog dlg = new MethodDialog(this.ioFrame, this.ioFrame.getTitle() + " ("
                    + type.getType() + " - " + type.getMsg() + ")", mmi, true);
            // dlg.pack();
            dlg.setVisible(true);
            if (dlg.isSetters()) {
                if (constructores.getActive() == null) {

                } else
                    this.setters(constructores.getActive().getReturnValue(), type.getType() + " - "
                            + type.getMsg());
            }
            dlg.dispose();
            ioFrame.removeLocationDialog();
            if (constructores.getActive() == null)
                return null;
            return constructores.getActive().getReturnValue();
        }
    }

    /**
     * @param clases
     * @return
     */
    public Object[] readForm(String[] clases) {
        return this.readForm(clases, clases);
    }

    public Object[] readForm(Class<?>[] clases) {
        if (clases != null) {
            String[] msgs = new String[clases.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = clases[i].getSimpleName();
            }
            return this.readForm(clases, msgs);
        } else {
            return null;
        }
    }

    public Object[] readForm(Object[] clases) {
        if (clases != null) {
            String[] msgs = new String[clases.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = clases[i].getClass().getSimpleName();
            }
            return this.readForm(clases, msgs);
        } else {
            return null;
        }
    }

    public Object[] readForm(String[] clases, String[] msgs) {
        if (clases == null)
            return null;
        if (msgs != null && clases.length != msgs.length) {
            System.out.println("WARNING (IO): Los tamaños de los arrays no coinciden: "
                    + clases.length + "-" + msgs.length);
            return null;
        }
        InputType[] types = new InputType[clases.length];
        for (int i = 0; i < clases.length; i++) {
            if (msgs != null)
                types[i] = new InputType(clases[i], msgs[i]);
            else
                types[i] = new InputType(clases[i]);
        }
        return this.readFormTypes(types);
    }

    public Object[] readForm(Class<?>[] clases, String[] msgs) {
        if (clases == null)
            return null;
        String[] clasesNames = new String[clases.length];
        for (int i = 0; i < clasesNames.length; i++) {
            clasesNames[i] = clases[i].getName();
        }
        return this.readForm(clasesNames, msgs);
    }

    public Object[] readForm(Object[] objs, String[] msgs) {
        if (objs == null)
            return null;
        if (msgs != null && objs.length != msgs.length) {
            System.out.println("WARNING (IO): Los tama�os de los arrays no coinciden: "
                    + objs.length + "-" + msgs.length);
            return null;
        }
        InputType[] types = new InputType[objs.length];
        for (int i = 0; i < objs.length; i++) {
            if (msgs != null)
                types[i] = new InputType(objs[i].getClass().getName(), msgs[i]);
            else
                types[i] = new InputType(objs[i].getClass().getName());
            types[i].setValue(objs[i]);
        }
        return this.readFormTypes(types);
    }

    private Object[] readFormTypes(InputType[] types) {
        InputPanel[] inputs = new InputPanel[types.length];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new FactoryInputPanel().getInputPanel(types[i], this);
        }

        new MultipleDialog(this.ioFrame, this.ioFrame.getTitle(), inputs).setVisible(true);
        Object[] values = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            values[i] = types[i].getValue();
        }
        return values;
    }

    public File readOpenFile(String msg, String[] exts) {
        InputType type = new InputType("java.io.File", "Open " + msg);
        type.setValues(exts);
        return (File) this.readType(type);
    }

    public File readOpenFile(String msg) {
        return this.readOpenFile(msg, null);
    }

    public File readOpenFile() {
        return this.readOpenFile("file", null);
    }

    public File readSaveFile(String msg, String[] exts) {
        InputType type = new InputType("java.io.File", "Save " + msg);
        type.setValues(exts);
        return (File) this.readType(type);
    }

    public File readSaveFile(String msg) {
        return this.readSaveFile(msg, null);
    }

    public File readSaveFile() {
        return this.readSaveFile("file", null);
    }

    /**
     * Modifica un objeto con los método setters
     * 
     * @param obj Objeto a modificar
     * @param msg Mensaje asociado
     * @return Objeto modificado
     */
    public Object setters(Object obj, String msg) {
        CollectionOfAbstracMethod setters = new SettersBuilder(obj).create();
        if (setters.getList().size() == 0) {
            System.out.println("WARNING (IO): Objeto sin setters públicos.");
            return obj;
        }
        MultipleMethodInput mmi = new MultipleMethodInput(setters, this);
        MethodDialog dlg = new MethodDialog(this.ioFrame, this.ioFrame.getTitle() + " (" + msg
                + ")", mmi, false);
        dlg.pack();
        dlg.setVisible(true);
        dlg.dispose();
        ioFrame.removeLocationDialog();
        return obj;
    }

    public void setters(Object obj) {
        if (obj == null)
            return;
        this.setters(obj, obj.getClass().getName());
    }

    /**
     * Muestra una selección de objetos para que se elija uno. Se apoya en el
     * método toString para mostrar los objetos
     * 
     * @param objs Array de objetos
     * @param msg mensaje asociado
     * @return Objeto seleccionado
     */
    public Object select(int min, int max, String msg) {
        if (min > max)
            System.out
                    .println("WARNING (IO): El valor mínimo no puede ser superio al valor máximo.");
        Integer[] valores = new Integer[max + 1 - min];
        for (int i = 0; i < valores.length; i++)
            valores[i] = new Integer(i + min);
        return this.select(valores, msg);
    }

    public Object select(int max, String msg) {
        return this.select(0, max, msg);
    }

    public Object select(int max) {
        return this.select(0, max, "Select");
    }

    public Object select(Object[] objs, String msg) {
        if (objs == null)
            return null;
        InputType type = new InputType(msg, objs);
        new SingleDialog(this.ioFrame, this.ioFrame.getTitle(),
                new FactoryInputPanel().getInputPanel(type, this)).setVisible(true);
        return type.getValue();
    }

    public Object select(Object[] objs) {
        return this.select(objs, "Select");
    }

    // ------------ Lecturas simplificadas----------------------
    public String readString(String msg) {
        return (String) this.read("String", msg);
    }

    public String readString() {
        return this.readString("String");
    }

    public int readInt(String msg) {
        return (Integer) this.read("Integer", msg);
    }

    public int readInt() {
        return this.readInt("Integer");
    }

    public double readDouble(String msg) {
        return (Double) this.read("Double", msg);
    }

    public double readDouble() {
        return this.readDouble("Double");
    }

    // =============================== Escrituras ====================
    public void setLog(Log log) {
        this.log = log;
    }

    public void setPrintLog(Log log) {
        this.printLog = log;
    }

    // --------------- Salidas ---------------------------------------
    public void print(Object msg, Log log) {
        if (this.log == Log.NONE || log == Log.NONE)
            return;
        if (log.getLevel() <= this.log.getLevel()) {
            String prefijo = "";
            if (log.getLevel() > Log.INFO.getLevel())
                prefijo = log.toString() + ">>> ";
            if (msg == null)
                this.ioFrame.addText(prefijo + "null");
            else if (msg.getClass().isArray()) {
                Array array = new Array(msg);
                this.ioFrame.addText(prefijo + array.toString());
            } else
                this.ioFrame.addText(prefijo + msg.toString());
        }
    }

    public void print(Object msg) {
        this.print(msg, this.printLog);
    }

    // ------------------- println --------------------------------
    public void println(Object msg, Log log) {
        this.print(msg, log);
        this.print("\r" + "\n", Log.INFO);
    }

    public void println(Object msg) {
        this.println(msg, this.printLog);
    }

    public void println() {
        this.println("");
    }

    // -------------------- otros ----------------------------------
    public void clear() {
        this.ioFrame.clear();
    }

    // ========================================================================

    public void setStatusBar(String msg) {
        ioFrame.setStatusBar(msg);
    }

    public void addview(Object view) {
        this.addView(view, true);
    }

    public void addView(Object view, boolean viewButton) {
        if (view != null) {
            this.ioFrame.addAction(view.getClass().getName(), new ActionBuilder(view).create()
                    .getList(), viewButton);
        }
    }

    public void addViewPanel(Object view) {
        if (view != null) {
            this.ioFrame.addViewPanel(view);
        }
    }

    public void setEnabledButton(String name, boolean b) {
        this.ioFrame.setEnablebButton(name, b);
    }

    public void setPath(String path) {
        this.chooser.setCurrentDirectory(new File(path));
    }

    public static void main(String[] args) {
        IO.getIO().println("Clase IO...");
    }
}
