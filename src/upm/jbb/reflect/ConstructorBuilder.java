package upm.jbb.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import upm.jbb.io.InputType;

public class ConstructorBuilder extends AbstractMethodBuilder {

	public ConstructorBuilder(String clase) {
		super(clase);
	}

	@Override
	public CollectionOfAbstracMethod create() {
		CollectionOfAbstracMethod cam = new CollectionOfAbstracMethod();
		if (this.getClase()==null)return null;
		Constructor<?>[] constructores = this.getClase().getConstructors();
		for (Constructor<?> constructor : constructores) {
			List<InputType> listaInputType = new ArrayList<InputType>();
			Type[] params = constructor.getGenericParameterTypes();
			for (Type type : params) {
				listaInputType.add(new InputType(type.toString()));
			}
			AbstractMethod am = new ConcreteConstructor(listaInputType, constructor, cam);
			cam.add(am);
		}
		return cam;
	}

}
