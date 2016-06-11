package upm.jbb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import upm.jbb.io.InputType;

public class MethodBuilder extends AbstractMethodBuilder {

	public MethodBuilder(Object instancia) {
		super(instancia);
	}

	@Override
	public CollectionOfAbstracMethod create() {
		CollectionOfAbstracMethod cam = new CollectionOfAbstracMethod();
		Method[] metodos = this.getClase().getMethods();
		for (Method metodo : metodos) {
			if (metodo.getName().equals("wait") || metodo.getName().equals("notify")
					|| metodo.getName().equals("notifyAll")) {
				continue;
			}
			List<InputType> listaInputType = new ArrayList<InputType>();
			Type[] params = metodo.getGenericParameterTypes();
			for (Type type : params) {
				listaInputType.add(new InputType(type.toString()));
			}
			AbstractMethod am = new ConcreteMethod(metodo.getName(), this.getInstancia(),
					listaInputType, metodo.getGenericReturnType().toString(), metodo, cam);
			cam.add(am);
		}
		return cam;
	}

}
