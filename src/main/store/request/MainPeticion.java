package main.store.request;

import upm.jbb.IO;

public class MainPeticion {
	
	private GestorComando gestor;

    public MainPeticion() {
        PeticionLocal peticionLocal = new PeticionLocal();
        PeticionRed peticionRed = new PeticionRed();
        this.gestor = new GestorComando();
        this.gestor.add(new ComandoLocal1(peticionLocal));
        this.gestor.add(new ComandoLocal2(peticionLocal));
        this.gestor.add(new ComandoRed1(peticionRed));
        this.gestor.add(new ComandoRed2(peticionRed));
        this.gestor.add(new ComandoRed3(peticionRed));
    }

    public void ejecutar() {
        String key = (String) IO.getIO().select(this.gestor.keys());
        this.gestor.execute(key);
    }

    public static void main(String[] args) {
        new MainPeticion();
    }

}
