package main.store.request;

public class ComandoRed1 extends ComandoGeneral{
	
	private static final String NAME = "Red1";

	public ComandoRed1(PeticionRed peticionRed) {
		super(peticionRed, NAME);
	}

	@Override
	public void execute() {
		this.getPeticionRed().red1();;;
		
	}

}
