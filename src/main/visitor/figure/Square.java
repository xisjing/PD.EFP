package main.visitor.figure;

public class Square extends Figure {

    private double side;
    
    

    public Square(String description, double side) {
        super(description);
        this.side = side;
    }
    
    public double getSide() {
		
		return this.side;
	}

    @Override
	public void accept(Visitor visitor){
		visitor.visitSquare(this);
	}

    @Override
    public String toString() {
        return super.toString();
    }

	

}
