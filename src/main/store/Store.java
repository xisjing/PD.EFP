package main.store;

public class Store {	
		
	private StoreState storeState;
	
	
	public StoreState getStoreState() {
		return storeState;
	}

	public void setStoreState(StoreState storeState) {
		this.storeState = storeState;
	}
		
    // Se almacena un objeto
    public void write(String key, String value) {
    	storeState.write(this);
    }

    // Se lee un objeto
    public void read(String key) {
      storeState.read(this);;
    }

	
    
   
	


		
	
}
