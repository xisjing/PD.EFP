package main.visitor;

public interface Element {
    void accept(Visitor v);
}
