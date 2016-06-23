package main.flyweight.texto;

public class Parrafo extends Composite{
	
	@Override
	protected String finalDibujar() {
		
		return "\n";
	}


	@Override
	protected String mensaje() {
		return "Error a�adiendo a parrafo";
	}

	@Override
	protected void addComposite(Componente componente) {
		this.getComponentes().add(componente);
		
	}

	@Override
	protected void noAddComposite(Componente componente) {
		throw new UnsupportedOperationException("No se pueden a�adir a parrafo");
		
	}

}
