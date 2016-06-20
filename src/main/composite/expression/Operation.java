package main.composite.expression;

public abstract class Operation extends Expresion {
	
	private Expresion expresion1;
	private Expresion expresion2;
	
	public Operation(Expresion expresion1,Expresion expresion2){
		this.expresion1=expresion1;
		this.expresion2=expresion2;
	}
	
	

	@Override
	public  abstract int  operar();



	public Expresion getExpresion1() {
		return expresion1;
	}



	public void setExpresion1(Expresion expresion1) {
		this.expresion1 = expresion1;
	}



	public Expresion getExpresion2() {
		return expresion2;
	}



	public void setExpresion2(Expresion expresion2) {
		this.expresion2 = expresion2;
	}

}
