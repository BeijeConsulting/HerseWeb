package it.beije.bean;

public class Users {

	private Integer id;

	private String email;

	private String name;

	private String surname;

	private String password;

	// Costructors
	public Users() {
	}

	public Users(Users users) {
		this.id = users.getId();
		this.email = users.getEmail();
		this.name = users.getName();
		this.surname = users.getSurname();
		this.password = users.getPassword();
	}

	// Getter & Setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ToString with Fields
	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", name=" + name + ", surname=" + surname + ", password="
				+ password + " ]";
	}
}
//bisogna mettere che unique? no non va messo, nel cao sia duplicato ci pensa il db a generare un error
