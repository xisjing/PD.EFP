package upm.jbb.view;

import upm.jbb.IO;
import upm.jbb.reflect.AbstractMethod;
import upm.jbb.reflect.CollectionOfAbstracMethod;

public class MultipleMethodInputTab extends JTabbedPaneClose {
	private static final long serialVersionUID = 1L;

	public MultipleMethodInputTab(CollectionOfAbstracMethod cam, IO io) {
		for (AbstractMethod metodo : cam.getList()) {
			this.addTab(metodo.getName(), new MethodInput(metodo, io));
		}
		
	}

}
