package main.flyweight.texto;

import java.util.ArrayList;

public abstract class Composite extends Componente {
	
private ArrayList<Componente> componentes;
	
	public Composite(){
		componentes = new ArrayList<Componente>();
	}
	
	public ArrayList<Componente> getComponentes(){
		return componentes;
	}
	
	@Override
	public boolean isComposite() {
		return true;
	}
	
	@Override
	public String dibujar(boolean enMayusculas) {
		String caracterDibujar ="";
		for(int ComponenteNum = 0; ComponenteNum < this.getComponentes().size(); ComponenteNum++) {
			caracterDibujar += this.getComponentes().get(ComponenteNum).dibujar(enMayusculas);
		}
		
		return caracterDibujar + finalDibujar();
	}

	protected abstract String finalDibujar();
	
	public void delete(Componente componente){
		componentes.remove(componente);
	}
	
	@Override
	public void add(Componente componente) {
		if(componente.isComposite()){
			 addComposite(componente);
		}else if(!componente.isComposite()){
			 noAddComposite(componente);
		}else{
			assert false : mensaje();
		}
		
	}

	protected abstract void noAddComposite(Componente componente);
	
	protected abstract void addComposite(Componente componente);

	protected abstract String mensaje();

	


}
