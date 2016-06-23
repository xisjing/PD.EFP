package main.flyweight.texto;

public abstract class Componente {

   public abstract void add(Componente componente);
	
	public abstract String dibujar(boolean isMayusculas);
	
	public abstract boolean isComposite();

}
