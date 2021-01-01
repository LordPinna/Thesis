package it.blueface.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Entity
public class Phone{

	@Id
	private String extension;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String ip;

	@Column(nullable = false)
	private String port;

	@ManyToOne
	private Customer customer;

	public Phone() {
	}

	public Phone(String name, String extension, String ip, String port) {
		this.name = name;
		this.extension = extension;
		this.ip = ip;
		this.port = port;
	}

	//Getters & Setters        

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean equals(Object obj) {
		Phone phonedevice = (Phone)obj;
		return this.extension.equals(phonedevice.getExtension());
	}

	public int hashCode() {
		return this.extension.hashCode();
	}

}