package upm.jbb.view;

import upm.jbb.IO;
import upm.jbb.reflect.AbstractMethod;
import upm.jbb.reflect.CollectionOfAbstracMethod;

public class MultipleMethodInput extends JPanelGridBag {
	private static final long serialVersionUID = 1L;

	public MultipleMethodInput(CollectionOfAbstracMethod cam, IO io) {
		for (AbstractMethod metodo : cam.getList()) {
			this.addComponent(metodo.getName(), new MethodInput(metodo, io));
		}
	}

}