package main.store;

public abstract class StoreState {
	
	    protected static Local local;
	    
	    protected static Net net;
	    
	    public static Local getLocal() {
	        return new Local();
	    }
	    
	    public static Net getNet() {
	        return new Net();
	    }
	    
	    public StoreState(Local local) {
	        StoreState.local = local;
	    }

		public StoreState(Net net) {
	        StoreState.net = net;
	    }
	    public abstract void read(Store store);

		public abstract void write(Store store);

	

}
