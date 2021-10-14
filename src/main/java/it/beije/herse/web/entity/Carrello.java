package it.beije.herse.web.entity;

import java.util.HashMap;
import java.util.List;

public class Carrello {

	private HashMap<Product, Integer> carrello = new HashMap<>();

	public HashMap<Product, Integer> getCarrello() {
		return carrello;
	}

	public void setCarrello(HashMap<Product, Integer> carrello) {
		this.carrello = carrello;
		
	}

}
