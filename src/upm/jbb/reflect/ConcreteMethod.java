package upm.jbb.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import upm.jbb.io.InputType;

public class ConcreteMethod extends AbstractMethod {
	private java.lang.reflect.Method metodo;

	public ConcreteMethod(String name, Object instancia, List<InputType> params, String returnType,
			java.lang.reflect.Method metodo, CollectionOfAbstracMethod owner) {
		super(name, instancia, params, returnType, owner);
		this.metodo = metodo;
	}

	@Override
	public void execute() {
		this.setReturnValue(null);
		this.setError("");
		Object[] params = new Object[this.getParams().size()];
		for (int p = 0; p < params.length; p++) {
			params[p] = this.getParams().get(p).getValue();
		}
		try {
			this.setReturnValue(this.metodo.invoke(this.getInstancia(), params));
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG (IOClass): method argument " + e);
		} catch (IllegalAccessException e) {
			System.out.println("DEBUG (IOClass): method access " + e);
		} catch (InvocationTargetException e) {
			System.out.println("INFO  (IOClass): method (" + this.getName() + ") >> "
					+ e.getCause());
			e.printStackTrace();
			this.setError("Error: " + e.getCause().toString());
		}
		this.getOwner().setActive(this);
	}

}
