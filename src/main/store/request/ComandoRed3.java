package main.store.request;

public class ComandoRed3 extends ComandoGeneral{
	
	private static final String NAME = "Red3";

	public ComandoRed3(PeticionRed peticionRed) {
		super(peticionRed, NAME);
	}

	@Override
	public void execute() {
		this.getPeticionRed().red3();
		
	}

}