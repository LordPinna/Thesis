package it.blueface.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
public class Customer{

	@Id
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private double credit;

	@Column
	private String email;

	@OneToMany(mappedBy = "customer", fetch=FetchType.EAGER)
	private List<Phone> phones;

	public Customer() {
	}

	public Customer(String username, String password, String name, String surname, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.credit = 0;
		this.email = email;
	}

	//Getters & Setters        

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCredit() {
		return this.credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public List<Phone> getPhones() {
		return this.phones;
	}

	public void addPhone(Phone phone) {
		this.phones.add(phone);
	}

	public boolean equals(Object obj) {
		Customer user = (Customer)obj;
		return this.username.equals(user.getUsername());
	}

	public int hashCode() {
		return this.username.hashCode();
	}

}