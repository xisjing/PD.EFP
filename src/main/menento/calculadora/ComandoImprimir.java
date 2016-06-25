package main.menento.calculadora;

import upm.jbb.IO;

public class ComandoImprimir extends ComandoGeneral {
    
    private static final String NAME = "Imprimir";

    public ComandoImprimir(Calculadora calculadora) {
        super(calculadora, NAME);
    }

    @Override
    public void execute() {
        IO.getIO().println(this.getCalculadora().getTotal());
    }

}
