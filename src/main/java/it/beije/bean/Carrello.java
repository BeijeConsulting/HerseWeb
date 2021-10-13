package it.beije.bean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Carrello {
	private List<Products> prodotti = new ArrayList<Products>();
	private double totale;
	private double quantita;
	private Users user;
	
	public Carrello(HttpServletRequest request) {
		HttpSession session = request.getSession();
		this.user = (Users)session.getAttribute("authUser");
	}
	
	public List<Products> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<Products> prodotti) {
		this.prodotti = prodotti;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public double getQuantita() {
		return quantita;
	}
	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "Carrello [prodotti=" + prodotti + ", totale=" + totale + ", quantita=" + quantita + "]";
	}
	
	
}
