package main.store;

public class LocalStore extends StoreState{
	
	public LocalStore() {
		super(local);
		
	}
	
	@Override
	public void write(Store store) {
		if(Net.getNet().isAvailable()){
			store.setStoreState(new NetStore());
		}
		store.setStoreState(new LocalStore());		
		
	}

	@Override
	public void read(Store store) {
		if(Net.getNet().isAvailable()){
			store.setStoreState(new NetStore());
		}
		store.setStoreState(new LocalStore());		
			
	}

	

	

}
