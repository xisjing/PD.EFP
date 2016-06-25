package main.menento.calculadora;

import upm.jbb.IO;

public class ComandoDeshacer extends ComandoGeneral implements Comando {
    
    private static final String NAME = "Deshacer";

    private GestorMementos<MementoCalculadora> gestorMementos;
    
    public ComandoDeshacer(Calculadora calculadora, GestorMementos<MementoCalculadora> gestorMementos) {
        super(calculadora, NAME);
        this.gestorMementos = gestorMementos;
    }

    @Override
    public void execute() {
        CalculadoraMementable calc = (CalculadoraMementable)this.getCalculadora();
        calc.restoreMemento(this.gestorMementos.getMemento((String) IO.getIO().select(gestorMementos.keys(), "Restaurar")));
    }

}
