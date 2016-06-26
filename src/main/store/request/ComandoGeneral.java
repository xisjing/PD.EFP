package main.store.request;

public abstract class ComandoGeneral implements Comando {
    
    private String name;
    private PeticionLocal peticionLocal;
    private PeticionRed peticionRed;
    
    public ComandoGeneral(PeticionLocal peticionLocal, String name) {
        this.peticionLocal = peticionLocal;
        this.name = name;
    }
    
    public ComandoGeneral(PeticionRed peticionRed, String name) {
        this.peticionRed = peticionRed;
        this.name = name;
    }
    
    @Override
    public String name() {
        return this.name;
    }
    
    
    protected PeticionLocal getPeticionLocal() {
        return peticionLocal;
    }
    
    protected PeticionRed getPeticionRed() {
        return peticionRed;
    }

}
