package main.menento.calculadora;

public class ComandoGuardar extends ComandoGeneral implements Comando {
    
    private static final String NAME = "Guardar";
    private static int mementoID = 0;
    
    private GestorMementos<MementoCalculadora> gestorMementos;

    public ComandoGuardar(Calculadora calculadora, GestorMementos<MementoCalculadora> gestorMementos) {
        super(calculadora, NAME);
        this.gestorMementos = gestorMementos;
    }

    @Override
    public void execute() {
        CalculadoraMementable calc = (CalculadoraMementable)this.getCalculadora();
        this.gestorMementos.addMemento(Integer.toString(mementoID++), calc.createMemento());
    }

}
