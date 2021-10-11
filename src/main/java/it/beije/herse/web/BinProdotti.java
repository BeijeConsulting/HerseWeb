package it.beije.herse.web;




public class BinProdotti {


	private Integer id;
	

	private String name;
	

	private String description;
	

	private Double price;
	

	private Integer quantity;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	
	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", name: ").append(name)
				.append(", description: ").append(description)
				.append(", price: ").append(price)
				.append(", quantity: ").append(quantity)
				.append("}");
		
		return builder.toString();
	}
	
}
