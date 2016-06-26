package main.store.request;

public class ComandoLocal2 extends ComandoGeneral{
	
	private static final String NAME = "Local2";

	public ComandoLocal2(PeticionLocal peticionLocal) {
		super(peticionLocal, NAME);
	}

	@Override
	public void execute() {
		this.getPeticionLocal().local2();;
		
	}
}
