package main.menento.calculadora;

import upm.jbb.IO;

public class ComandoSumar extends ComandoGeneral {

    private static final String NAME = "Sumar";

    public ComandoSumar(Calculadora calculadora) {
        super(calculadora, NAME);
    }

    @Override
    public void execute() {
        this.getCalculadora().sumar(IO.getIO().readInt());
    }
}
