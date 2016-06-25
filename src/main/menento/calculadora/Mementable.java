package main.menento.calculadora;

public interface Mementable<T> {
	
	T createMemento();

    void restoreMemento(T memento);

}
