package it.polito.tdp.parole.model;

import java.util.*;

public class Parole {
	
	//private List<String> lista = new LinkedList<String> ();
	private List<String> lista = new ArrayList<String> ();
		
	public Parole() {
	}
	
	public void addParola(String p) {
		lista.add(p);
	}
	
	public List<String> getElenco() {
		return lista;
	}
	
	public void reset() {
		lista.removeAll(lista);
	}
	
	public boolean cancella(String s) {
		if (lista.remove(s) == true)
			return true;
		return false;
	}

}
