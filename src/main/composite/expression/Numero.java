package main.composite.expression;

public class Numero extends Expresion {
	
	private int numero;
	
	public Numero(int numero){
		this.numero = numero;
	}
	

	@Override
	public int operar() {	
		return numero;
	}
	
	@Override
	public String toString(){
		return String.valueOf(numero);
	}

}
