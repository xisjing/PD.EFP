package upm.jbb.reflect;

public abstract class AbstractMethodBuilder {

	private Object instancia = null;
	private Class<? extends Object> clase = null;

	public AbstractMethodBuilder(Object instancia) {
		if (instancia == null)
			throw new RuntimeException(
					"DEBUG (IOClass) No existe instancia para poder preparar los metodos");
		this.instancia = instancia;
		this.clase = instancia.getClass();
	}

	public AbstractMethodBuilder(String clase) {
		try {
			this.clase = Class.forName(clase);
		} catch (ClassNotFoundException e) {
			System.out.println("WARNING (IO): Clase no encontrada: " + clase);
		} catch (NoClassDefFoundError e) {
			System.out.println("WARNING (IO): Nombre de clase incorrecto: " + clase);
		}
	}

	protected Object getInstancia() {
		return instancia;
	}

	protected Class<? extends Object> getClase() {
		return clase;
	}

	public abstract CollectionOfAbstracMethod create();
}
