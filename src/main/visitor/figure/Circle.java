package main.visitor.figure;

public class Circle extends Figure {

    private double radius;

    public Circle(String description, double radius) {
        super(description);
        this.radius = radius;
    }
    
    public double getradius() {
		
		return this.radius;
	}

    @Override
	public void accept(Visitor visitor){
		visitor.visitCircle(this);
	}

    

	

}
