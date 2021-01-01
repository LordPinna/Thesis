package it.blueface.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CustomerFacade {

	private EntityManagerFactory emf;
	private EntityManager em;

	public CustomerFacade() {
		this.emf = Persistence.createEntityManagerFactory("thesis-unit");
		this.em = emf.createEntityManager();
	}

	public Customer createCustomer(String username, String password, String name, String surname, String email){
		Customer customer = new Customer(username, password, name, surname, email);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(customer);
		tx.commit();
		em.close();
		emf.close();
		return customer;
	}

	public Customer getCustomer(String username, String password) {
		Customer customer = null;
		Customer c = em.find(Customer.class, username);
		if(c.getPassword().equals(password))
			customer = c;
		em.close();
		emf.close();
		return customer;
	}

	public double createConference(String username){
		Customer customer = em.find(Customer.class, username);
		double credit = customer.getCredit() - 1 ;
		Query query = em.createQuery("UPDATE Customer c SET c.credit = :credit WHERE c.username = :username");
		query.setParameter("credit", credit);
		query.setParameter("username", username);
		query.executeUpdate();
		em.close();
		emf.close();
		return credit;
	}
}

