package main.store.request;

public class ComandoRed2 extends ComandoGeneral{
	
	private static final String NAME = "Red2";

	public ComandoRed2(PeticionRed peticionRed) {
		super(peticionRed, NAME);
	}

	@Override
	public void execute() {
		this.getPeticionRed().red2();;;
		
	}

}
