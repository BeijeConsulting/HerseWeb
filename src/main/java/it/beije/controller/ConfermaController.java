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
import it.beije.bean.Orders;
import it.beije.bean.Users;
import it.beije.model.OrderModel;

/**
 * Servlet implementation class ConfermaController
 */
@WebServlet("/ConfermaController")
public class ConfermaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfermaController() {
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
				List<OrderItems> orderItems = carrelloNew.getOrderItems();
				OrderModel orderModel = new OrderModel();
				if (orderItems.size() > 0) {
					// posso piazzare l'ordine perche il carrello non e vuoto
					Users user = (Users) session.getAttribute("authUser");
					int quantityOrder = 0;

					for (OrderItems oi : orderItems) {
						quantityOrder += oi.getQuantity();
					}

					int orderId = orderModel.placeOrder(user, quantityOrder);
					// pizzo poi gli order items e aggiorno le quantita nel db
					for (OrderItems oi : orderItems) {
						orderModel.placeOrderItems(oi, orderId);
					}
					CarrelloNew carrelloNew2 = new CarrelloNew(request);
					session.setAttribute("carrello", carrelloNew2);
					response.sendRedirect("Conferma.jsp");
				} else {
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
