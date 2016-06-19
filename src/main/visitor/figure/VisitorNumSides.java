package main.visitor.figure;

public class VisitorNumSides extends Visitor {
	
	private double sides;
	
	public double getSides() {
		return sides;
	}
	
	@Override
	public void visitCircle(Circle circle) {
		this.sides += Double.POSITIVE_INFINITY;
		
	}

	@Override
	public void visitSquare(Square square) {
		this.sides += 4;
		
	}

	@Override
	public void visitTriangle(Triangle triangle) {
		this.sides += 3;
		
	}


}
