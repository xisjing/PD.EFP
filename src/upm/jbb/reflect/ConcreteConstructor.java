package upm.jbb.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import upm.jbb.io.InputType;

public class ConcreteConstructor extends AbstractMethod {
	private java.lang.reflect.Constructor<?> constructor;

	public ConcreteConstructor(List<InputType> params, Constructor<?> constructor,CollectionOfAbstracMethod owner) {
		super("new(" + params.size() + ")", null, params, "Object",owner);
		this.constructor = constructor;
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
			this.setReturnValue(this.constructor.newInstance(params));
		} catch (IllegalArgumentException e) {
			System.out.println("DEBUG (Constructors IOClass argument): " + e.getCause());
		} catch (InstantiationException e) {
			System.out.println("DEBUG (Constructors IOClass instantiation): " + e.getCause());
		} catch (IllegalAccessException e) {
			System.out.println("DEBUG (Constructors IOClass accesss): " + e.getCause());
		} catch (InvocationTargetException e) {
			System.out
					.println("INFO (Constructors IOClass invocation): " + e.getCause().toString());
			this.setError("Error: " + e.getCause().toString());
		}
		this.getOwner().setActive(this);
	}

}
