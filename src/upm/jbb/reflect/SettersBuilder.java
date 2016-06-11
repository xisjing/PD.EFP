package upm.jbb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import upm.jbb.io.InputType;

public class SettersBuilder extends AbstractMethodBuilder {

	public SettersBuilder(Object instancia) {
		super(instancia);
	}

	@Override
	public CollectionOfAbstracMethod create() {
		CollectionOfAbstracMethod getters = new GettersBuilder(this.getInstancia()).create();
		getters.executeAll();
		
		CollectionOfAbstracMethod cam = new CollectionOfAbstracMethod();
		Method[] metodos = this.getClase().getMethods();
		for (Method metodo : metodos) {
			if (metodo.getName().matches("set[A-Z].*")) {
				if (metodo.getModifiers() != Modifier.PUBLIC) {
					System.out.println("WARNING (IOClass): setter no public (" + metodo.getName()
							+ ")");
					continue;
				} else if (metodo.getGenericParameterTypes().length != 1) {
					System.out.println("WARNING (IOClass): setter con parámetros <> 1 ("
							+ metodo.getName() + ")");
					continue;
				} else if (!metodo.getReturnType().getName().equals("void")) {
					System.out.println("WARNING (IOClass): setter no void (" + metodo.getName()
							+ ")");
					continue;
				} else {
					String key = metodo.getName().substring(3, 4).toLowerCase();
					if (metodo.getName().length() > 4) key = key + metodo.getName().substring(4);
					Type type = metodo.getGenericParameterTypes()[0];
					List<InputType> listaInputType = new ArrayList<InputType>();
					
					InputType in=new InputType(type.toString(), key);
					Object getValue=null;
					for (AbstractMethod metodoGet : getters.getList()) {
						if (metodoGet.getName().equals(key))
							getValue=metodoGet.getReturnValue();
					}
					
					in.setValue(getValue);
					listaInputType.add(in);
					AbstractMethod am = new ConcreteMethod(metodo.getName(), this.getInstancia(),
							listaInputType, metodo.getGenericReturnType().toString(), metodo, cam);
					cam.add(am);
				}
			}
		}
		return cam;
	}

}
