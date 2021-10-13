package it.beije.herse.web;
import java.util.*;

public class Carrello {
	
	// chiave id, valore quantità
	private HashMap<Integer,Integer> mappa = new HashMap<>();

	
	
	
	public HashMap<Integer, Integer> getMappa() {
		return mappa;
	}
	public void setMappa(HashMap<Integer, Integer> mappa) {
		this.mappa = mappa;
	}




	public void addProduct(Integer id, Integer quantita) {
		
		if(mappa.containsKey(id)) {
			
			mappa.replace(id, quantita + mappa.get(id));
		} else {
			mappa.put(id, quantita);
		}
	}
	
}
