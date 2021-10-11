package Shop;
import java.time.LocalDateTime;

import javax.persistence.*;
@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="creation_datetime")
	private LocalDateTime dateTime;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="user_id")
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDataTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", userId: ").append(userId)
				.append(", amount: ").append(amount)
				.append(", dateTime: ").append(dateTime)
				.append("}");
		return builder.toString();
	}
}
