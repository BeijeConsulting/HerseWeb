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
 * Servlet implementation class AggiornaController
 */
@WebServlet("/AggiornaController")
public class AggiornaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiornaController() {
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
				if (carrelloNew.getOrderItems().size() > 0) {
					List<OrderItems> orderItems = carrelloNew.getOrderItems();
					int myProdId;
					String strNewQuantity;

					for (OrderItems oi : orderItems) {
						int newQuantity;

						myProdId = oi.getProductId();
						strNewQuantity = request.getParameter("" + myProdId);
						newQuantity = Integer.parseInt(strNewQuantity);
						carrelloNew.UpdateQuantity(oi, newQuantity);
						if(orderItems.size()==0) {
							break;
						}

					}
					response.sendRedirect("Carrello.jsp");
				} else {
					// carrello vuoto
					String errorCarEmpty = "errore carrello";
					session.setAttribute("errorCarEmpty", errorCarEmpty);
					response.sendRedirect("Carrello.jsp");
				}
			} else {
				// qualcosa non va con il carrello
				String errorCar = "errore carrello";
				session.setAttribute("errorCar", errorCar);
				response.sendRedirect("Carrello.jsp");
			}
		} else {
			// utente non loggato
			String errorAuth = "errore user log";
			session.setAttribute("errorAuth", errorAuth);
			response.sendRedirect("Carrello.jsp");
		}
	}

}
