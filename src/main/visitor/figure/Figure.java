package main.visitor.figure;

public abstract class Figure {

    private String description;
    
    public String getDescription(){
    	return description;
    }
	
    public Figure(String description) {}

    
    public void accept(Visitor visitor){}

}
