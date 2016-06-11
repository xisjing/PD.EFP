package upm.jbb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import upm.jbb.io.InputType;

public class ActionBuilder extends AbstractMethodBuilder {

    public ActionBuilder(Object instancia) {
        super(instancia);
    }

    @Override
    public CollectionOfAbstracMethod create() {
        CollectionOfAbstracMethod cam = new CollectionOfAbstracMethod();
        Method[] metodos = this.getClase().getDeclaredMethods();
        for (Method metodo : metodos) {
            if (metodo.getModifiers() != Modifier.PUBLIC) {
                continue;
            } else if (metodo.getGenericParameterTypes().length > 0) {
                System.out.println("WARNING (IOClass): action con par�metros > 0 ("
                        + metodo.getName() + ")");
                continue;
            } else if (!metodo.getReturnType().getName().equals("void")) {
                System.out.println("WARNING (IOClass): action no void (" + metodo.getName() + ")");
                continue;
            } else {
                List<InputType> listaInputType = new ArrayList<InputType>();
                Type[] params = metodo.getGenericParameterTypes();
                for (Type type : params) {
                    listaInputType.add(new InputType(type.toString()));
                }
                AbstractMethod am = new ConcreteMethod(metodo.getName(), this.getInstancia(),
                        listaInputType, metodo.getGenericReturnType().toString(), metodo, cam);
                cam.add(am);
            }
        }

        return cam;
    }

}
