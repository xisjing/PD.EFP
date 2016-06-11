package upm.jbb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import upm.jbb.io.InputType;

public class GettersBuilder extends AbstractMethodBuilder {

	public GettersBuilder(Object instancia) {
		super(instancia);
	}

	@Override
	public CollectionOfAbstracMethod create() {
		CollectionOfAbstracMethod cam = new CollectionOfAbstracMethod();
		Method[] metodos = this.getClase().getMethods();
		for (Method metodo : metodos) {
			if (metodo.getName().matches("(get[A-Z].*)|(is[A-Z].*)")) {
				if (metodo.getModifiers() != Modifier.PUBLIC) {
					if (!metodo.getName().equals("getClass"))
						System.out.println("WARNING (IOClass): getter no public ("
								+ metodo.getName() + ")");
					continue;
				} else if (metodo.getGenericParameterTypes().length > 0) {
					System.out.println("WARNING (IOClass): getter con parámetros ("
							+ metodo.getName() + ")");
					continue;
				} else if (metodo.getReturnType().getName().equals("void")) {
					System.out.println("WARNING (IOClass): getter con void (" + metodo.getName()
							+ ")");
					continue;
				} else {
					String key;
					if (metodo.getName().substring(0, 2).equals("is")) {
						key = metodo.getName().substring(2, 3).toLowerCase();
						if (metodo.getName().length() > 3)
							key = key + metodo.getName().substring(3);
					} else {
						key = metodo.getName().substring(3, 4).toLowerCase();
						if (metodo.getName().length() > 4)
							key = key + metodo.getName().substring(4);
					}
					AbstractMethod am = new ConcreteMethod(key, this.getInstancia(),
							new ArrayList<InputType>(), metodo.getGenericReturnType().toString(), metodo, cam);
					cam.add(am);
				}
			}
		}
		return cam;

	}

}
