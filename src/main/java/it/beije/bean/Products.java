package it.beije.bean;

public class Products {

	private Integer id;

	private String description;

	private String name;

	private Double price;

	private Integer quantity;

	// Getter & Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	// ToString using Fields
	@Override
	public String toString() {
		return "Products [id=" + id + ", description=" + description + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
