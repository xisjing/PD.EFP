package main.menento.calculadora;

public abstract class ComandoGeneral implements Comando {

	private String name;
    private Calculadora calculadora;
    
    public ComandoGeneral(Calculadora calculadora, String name) {
        this.calculadora = calculadora;
        this.name = name;
    }
    
    @Override
    public String name() {
        return this.name;
    }
    
    protected Calculadora getCalculadora() {
        return calculadora;
    }


}
