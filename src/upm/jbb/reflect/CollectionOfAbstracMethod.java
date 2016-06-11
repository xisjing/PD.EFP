package upm.jbb.reflect;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfAbstracMethod {
	private AbstractMethod active;
	private List<AbstractMethod> list;

	public CollectionOfAbstracMethod() {
		this.list= new ArrayList<AbstractMethod>();
	}

	public AbstractMethod getActive() {
		return active;
	}

	public void setActive(AbstractMethod active) {
		this.active = active;
	}

	public List<AbstractMethod> getList() {
		return list;
	}

	public void setList(List<AbstractMethod> list) {
		this.list = list;
	}

	public void add(AbstractMethod am) {
		this.list.add(am);
	}
	
	public void executeAll(){
		for (AbstractMethod metodo : this.list) {
			metodo.execute();
		}
	}
}