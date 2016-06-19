package main.visitor.figure;

public class Triangle extends Figure {
	
    private double base;

    private double height;

    public Triangle(String description, double base, double height) {
        super(description);
        this.base = base;
        this.height = height;
    }
    
    public double getBase() {
		return this.base;
	}

	public double getHeight() {
		return this.height;
		
	}
	
	 @Override
	    public void accept(Visitor visitor){
			visitor.visitTriangle(this);
		}
 
    @Override
    public String toString() {
        return super.toString();
    }

	

}
