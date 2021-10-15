package it.beije.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.beije.model.ProductsModel;

public class CarrelloNew {

	List<OrderItems> orderItems = new ArrayList<OrderItems>();
	Users user;

	public CarrelloNew(HttpServletRequest request) {
		HttpSession session = request.getSession();
		this.user = (Users) session.getAttribute("authUser");
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void addOrderItems(OrderItems orderItems) {
		this.orderItems.add(orderItems);
	}

	public void removeOrderItems(OrderItems orderItems) {
		this.orderItems.remove(orderItems);
	}

	public void UpdateProducts(OrderItems orderItem) {
		boolean found = false;
		if (this.orderItems.size() == 0) {
			if (orderItem.getQuantity() != 0) {
				// Controllo che sia una quantita valida altrimenti non faccio niente
				this.orderItems.add(orderItem);
			}
		} else {
			// la mia lista contiene gia degli elamenti
			for (OrderItems oi : this.orderItems) {
				if (oi.getProductId() == orderItem.getProductId()) {
					// esiste gia e va modificata la quantity
					found = true;
					if (orderItem.getQuantity() != 0) {
						int oldQuantity = oi.getQuantity();
						int newQuantity = oldQuantity + orderItem.getQuantity();
						if (newQuantity < 11) {
							// fino a un massiomo di 10 prodotti
							if (productAvaible(orderItem.getProductId(), newQuantity)) {
								oi.setQuantity(newQuantity);
								double newPrice = (oi.getSellPrice() / oldQuantity) * newQuantity;
								oi.setSellPrice((int) newPrice);
							}else {
								int avaibleQuantity = getAvaibleQuantity(orderItem.getProductId());
								oi.setQuantity(avaibleQuantity);
								double newPrice = (oi.getSellPrice() / oldQuantity) * avaibleQuantity;
								oi.setSellPrice((int) newPrice);
							}
						} else {
							oi.setQuantity(10);
							double newPrice = (oi.getSellPrice() / oldQuantity) * 10;
							oi.setSellPrice((int) newPrice);
						}
						break;
					}
				}
			}
			// non esiste ancora e quindi posso aggiungerlo alla lista
			if (orderItem.getQuantity() != 0 && !found) {
				this.orderItems.add(orderItem);
			}
		}
	}

	public boolean productAvaible(int productId, int necessaryQuantity) {
		boolean avaibleQuantity = true;
		ProductsModel productsModel = new ProductsModel();
		List<Products> products = productsModel.getProducts();

		for (Products p : products) {
			if (p.getId() == productId) {
				if (p.getQuantity() <= necessaryQuantity) {
					avaibleQuantity = false;
					return avaibleQuantity;
				}
			}
		}

		return avaibleQuantity;
	}

	public int getAvaibleQuantity(int productId) {
		int avaibleQuantity = 0;

		ProductsModel productsModel = new ProductsModel();
		List<Products> products = productsModel.getProducts();

		for (Products p : products) {
			if (p.getId() == productId) {

				avaibleQuantity = p.getQuantity();
				return avaibleQuantity;
			}
		}

		return avaibleQuantity;
	}

	public String printProducts(OrderItems orderItem) {
		ProductsModel pm = new ProductsModel();
		List<Products> prodotti = pm.getProducts();

		for (Products p : prodotti) {
			if (p.getId() == orderItem.getProductId()) {
				// Se trova lelemento crea lo string builder
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("NAME: ").append(p.getName()).append(" | ");
				stringBuilder.append("DESCRIZIONE: ").append(p.getDescription()).append(" | ");
				stringBuilder.append("PREZZO: ").append(p.getPrice()).append(" | ");
				stringBuilder.append("QUANTITA': ").append(p.getQuantity());
				return stringBuilder.toString();
			}
		}
		// se non lo trova ritorna null
		return null;
	}

	public void UpdateQuantity(OrderItems orderItem, int newQuantity) {

		for (OrderItems oi : this.orderItems) {
			if (oi.getProductId() == orderItem.getProductId()) {
				// esiste gia e va modificata la quantity
				if (newQuantity != 0) {
					// se la quantita e divers a da 0
					int oldQuantity = oi.getQuantity();
					if (oldQuantity != newQuantity) {
						// se sono diverse le aggiorno altrimenti non faccio nulla
						if (productAvaible(orderItem.getProductId(), newQuantity)) {
							double newPrice = (oi.getSellPrice() / oldQuantity) * newQuantity;
							oi.setQuantity(newQuantity);
							oi.setSellPrice((int) newPrice);
						} else {
							int avaibleQuantity = getAvaibleQuantity(orderItem.getProductId());
							double newPrice = (oi.getSellPrice() / oldQuantity) * avaibleQuantity;
							oi.setQuantity(avaibleQuantity);
							oi.setSellPrice((int) newPrice);
						}
					}
				} else {
					// nuova quantita = 0 quindi rimuovo l'articolo
					this.orderItems.remove(oi);
				}
				break;
			}
		}

	}

	public int getNumProduct() {
		int tot = 0;
		for (OrderItems oi : this.orderItems) {
			tot += oi.getQuantity();
		}
		return tot;
	}

	public int getTotPrice() {
		int tot = 0;
		for (OrderItems oi : this.orderItems) {
			tot += oi.getSellPrice();
		}
		return tot;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ ");
		for (OrderItems oi : orderItems) {
			stringBuilder.append(oi.toString()).append(" ,");
		}
		stringBuilder.append(" }");
		return stringBuilder.toString();
	}

//	public Users getUser() {
//		return user;
//	}
//
//	public void setUser(Users user) {
//		this.user = user;
//	}

}
