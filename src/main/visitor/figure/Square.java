package main.visitor.figure;

public class Square extends Figure {

    private double side;

    public Square(String description, double side) {
        super(description);
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public double numberOfSides() {
        return 4;
    }

    @Override
    public String toString() {
        return super.toString();
    }

	public int getSide() {
		// TODO Auto-generated method stub
		return 0;
	}

}
