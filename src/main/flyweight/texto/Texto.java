package main.flyweight.texto;

public class Texto extends Composite {
	
	@Override
	protected String finalDibujar() {
		
		return "---o---\n";
	}


	@Override
	protected String mensaje() {
		return "Error añadiendo a texto";
	}

	@Override
	protected void addComposite(Componente componente) {
		this.getComponentes().add(componente);
		
	}

	@Override
	protected void noAddComposite(Componente componente) {
		throw new UnsupportedOperationException("No se pueden añadir a texto");
		
	}

}
