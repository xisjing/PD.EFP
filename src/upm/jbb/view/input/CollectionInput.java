package upm.jbb.view.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import upm.jbb.IO;
import upm.jbb.io.InputType;

public class CollectionInput extends CompositeInput implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Collection<Object> coleccion;

	public CollectionInput(InputType type, IO io) {
		super(type, io);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Se crean las collecciones
		if (this.coleccion == null) {
			if (this.getInputType().genericName().equals("java.util.List")) this.coleccion = new ArrayList<Object>();
			else if (this.getInputType().genericName().equals("java.util.Set")) this.coleccion = new HashSet<Object>();
			else if (this.getInputType().genericName().equals("java.util.SortedSet")) this.coleccion = new TreeSet<Object>();
			else if (this.getInputType().genericName().equals("java.util.Queue")
					|| this.getInputType().genericName().equals("java.util.Deque"))
				this.coleccion = new LinkedList<Object>();
			this.getInputType().setValue(this.coleccion);
		}

		Object element = this.getIO().read(this.getInputType().genericType(),
				this.getInputType().genericType());
		if (element != null) {
			this.coleccion.add(element);
			this.getInputType().notifyObserver();
		}
	}

}
