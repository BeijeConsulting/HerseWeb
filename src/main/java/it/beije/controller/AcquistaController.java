package it.beije.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.bean.CarrelloNew;
import it.beije.bean.OrderItems;
import it.beije.bean.Products;
import it.beije.model.ProductsModel;

/**
 * Servlet implementation class AcquistaController
 */
@WebServlet("/AcquistaController")
public class AcquistaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AcquistaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		HttpSession session = request.getSession();

		if (session.getAttribute("authUser") != null) {
			if (session.getAttribute("carrello") != null) {
				CarrelloNew carrelloNew = (CarrelloNew) session.getAttribute("carrello");
				ProductsModel productsModel = new ProductsModel();
				List<Products> products = productsModel.getProducts();
				String quantity;
				int productId;
				OrderItems orderItems = null;

				for (Products p : products) {

					int quantityInt;

					productId = productsModel.getProductId(p);
					quantity = request.getParameter("" + productId);
					quantityInt = Integer.parseInt(quantity);

					orderItems = new OrderItems();
					orderItems.setId(null);
					orderItems.setOrderId(null);
					orderItems.setProductId(productId);
					orderItems.setQuantity(quantityInt);
					double price = p.getPrice() * quantityInt;
					orderItems.setSellPrice((int) price);
					// aggiorna ,elimina e aggiunge un nuovo prodotto al carrello
					carrelloNew.UpdateProducts(orderItems);

				}
				response.sendRedirect("Carrello.jsp");
			} else {
				// qualcosa non va con il carrello
				String errorCar = "errore carrello";
				session.setAttribute("errorCar", errorCar);
				response.sendRedirect("Acquista.jsp");
			}
		} else {
			// utente non loggato
			String errorAuth = "errore user log";
			session.setAttribute("errorAuth", errorAuth);
			response.sendRedirect("Acquista.jsp");
		}

	}

}
