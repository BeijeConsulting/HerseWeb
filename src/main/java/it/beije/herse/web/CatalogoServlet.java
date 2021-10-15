package it.beije.herse.web;

import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Shop.*;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CatalogoServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));
		int quta = Integer.parseInt(request.getParameter("qta"));
		HttpSession session=request.getSession();
		EntityManager manager=(EntityManager)session.getAttribute("manager");
		if(controlloIdProdotto(idProdotto)&&controlloQuantità(idProdotto, quta)) {
			session.setAttribute("carrello", carrello(idProdotto,quta,(ArrayList<Carrello>)session.getAttribute("carrello"),(Order)session.getAttribute("order")));
			session.setAttribute("prodotto_aggiunto", "Prodotto aggiunto al carrello!");
		}else {
			session.setAttribute("prodotto_aggiunto", "ERRORE!");
		}
		response.sendRedirect("Catalogo.jsp");
	}
	protected  boolean controlloQuantità(int id,int qty) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		Product p=manager.find(Product.class, id);
		if(p.getQty()>=qty) {
			manager.getTransaction().begin();
			p.setQty(p.getQty()-qty);
			manager.getTransaction().commit();
			return true;
		}
		return false;
	}
	//
	protected  boolean controlloIdProdotto(Integer id) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		if(manager.find(Product.class, id)!=null)
			return true;
		return false;
	}
	
	protected  ArrayList<Carrello> carrello(Integer id_product,Integer qty,ArrayList<Carrello>carrello,Order order) {
		EntityManager manager=ShopEntityManager.newEntityManager();
		for(Carrello c:carrello) {
			if(id_product==c.getId_product()) {
				manager.getTransaction().begin();
				//manager.merge(order);
				order.setAmount(order.getAmount()+qty*(manager.find(Product.class, id_product).getPrice()));
				//manager.persist(order);
				manager.getTransaction().commit();
				c.setQty(c.getQty()+qty);
				return carrello;
			}}
		Carrello carr=new Carrello();
		carr.setId_product(id_product);
		carr.setQty(qty);
		carrello.add(carr);
		manager.getTransaction().begin();
		//manager.merge(order);
		order.setAmount(order.getAmount()+qty*manager.find(Product.class, id_product).getPrice());
		manager.getTransaction().commit();
		return carrello;
	}

}
