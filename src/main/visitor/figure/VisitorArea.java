package main.visitor.figure;

public class VisitorArea extends Visitor{
	
    private double area;
	
	public VisitorArea(){
		this.area = 0;
	}
	
	public double getArea(){
		return this.area;
	}

	@Override
	public void visitCircle(Circle circle) {
		area += (3.14 * (circle.getradius() * circle.getradius()));
		
	}

	@Override
	public void visitSquare(Square square) {
		area += (square.getSide() * square.getSide());
		
	}

	@Override
	public void visitTriangle(Triangle triangle) {
		area += (triangle.getBase() * triangle.getHeight()/2);
		
	}

}
