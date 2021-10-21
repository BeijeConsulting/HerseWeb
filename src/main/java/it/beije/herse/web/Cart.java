package it.beije.herse.web;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "`carrello`")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idCarrello")
	private Integer idCarrello;

	@Column(name="idProducts")
	private Integer idProduct;

	@Column(name="idUser")
	private Integer idUser;
	
	@Column(name="qProduct")
	private Integer qProduct;
	
	public Integer getqProduct() {
		return qProduct;
	}

	public void setqProduct(Integer qProduct) {
		this.qProduct = qProduct;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(Integer idCarrello) {
		this.idCarrello = idCarrello;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public String toString() {
		return "Cart [getIdUser()=" + getIdUser() + ", getIdCarrello()=" + getIdCarrello() + ", getIdProduct()="
				+ getIdProduct() + "]";
	}


	
	

	
	


}
