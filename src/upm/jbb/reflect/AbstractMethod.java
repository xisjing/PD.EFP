package upm.jbb.reflect;
import java.util.List;

import upm.jbb.io.InputType;
import upm.jbb.view.ActionCommand;
public abstract class AbstractMethod implements ActionCommand {
	private String name;
	private Object instancia;
	private List<InputType> params;
	private String returnType;
	private Object returnValue;
	private String error;
	// Para indicar en la colección el último método que se ejecutó
	private CollectionOfAbstracMethod owner;

	public AbstractMethod(String name, Object instancia, List<InputType> params, String returnType,
			CollectionOfAbstracMethod owner) {
		this.name = name;
		this.instancia = instancia;
		this.params = params;
		this.returnType = returnType;
		this.owner = owner;
	}

	@Override
	public abstract void execute();

	public String getName() {
		return name;
	}

	public Object getInstancia() {
		return instancia;
	}

	public List<InputType> getParams() {
		return params;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public String getReturnType() {
		return returnType;
	}

	public String getError() {
		return error;
	}

	protected void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	protected void setError(String error) {
		this.error = error;
	}

	protected CollectionOfAbstracMethod getOwner() {
		return owner;
	}

}