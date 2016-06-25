package main.menento.calculadora;

import upm.jbb.IO;

public class MainCalculadoraMementable {
	
	private GestorComandos gestorComandos;
    
    public MainCalculadoraMementable() {
        CalculadoraMementable calc = new CalculadoraMementable();
        GestorMementos<MementoCalculadora> gestorMementos = new GestorMementos<MementoCalculadora>();
        this.gestorComandos = new GestorComandos();
        this.gestorComandos.add(new ComandoSumar(calc));
        this.gestorComandos.add(new ComandoRestar(calc));
        this.gestorComandos.add(new ComandoIniciar(calc));
        this.gestorComandos.add(new ComandoImprimir(calc));
        this.gestorComandos.add(new ComandoGuardar(calc, gestorMementos));
        this.gestorComandos.add(new ComandoDeshacer(calc, gestorMementos));
        
    }

    public void ejecutar() {
        String key = (String) IO.getIO().select(this.gestorComandos.keys());
        this.gestorComandos.execute(key);
    }

    public static void main(String[] args) {
       new MainCalculadoraMementable();
    }

}
