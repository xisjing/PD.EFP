package main.store.request;

public class ComandoLocal1  extends ComandoGeneral{
	
	private static final String NAME = "Local1";

	public ComandoLocal1(PeticionLocal peticionLocal) {
		super(peticionLocal, NAME);
	}

	@Override
	public void execute() {
		this.getPeticionLocal().local1();;
		
	}

}
