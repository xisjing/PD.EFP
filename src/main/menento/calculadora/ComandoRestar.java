package main.menento.calculadora;

import upm.jbb.IO;

public class ComandoRestar extends ComandoGeneral {

    private static final String NAME = "Restar";
    
    public ComandoRestar(Calculadora calculadora) {
        super(calculadora, NAME);
    }
    
    @Override
    public void execute() {
        this.getCalculadora().restar(IO.getIO().readInt());
    }

}
