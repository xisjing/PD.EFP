package main.flyweight.texto;

public class Parrafo extends Composite{
	
	@Override
	protected String finalDibujar() {
		
		return "\n";
	}


	@Override
	protected String mensaje() {
		return "Error añadiendo a parrafo";
	}

	@Override
	protected void addComposite(Componente componente) {
		throw new UnsupportedOperationException("No se pueden añadir a parrafo");
		
		
	}

	@Override
	protected void noAddComposite(Componente componente) {
		this.getComponentes().add(componente);
			
	}

}
