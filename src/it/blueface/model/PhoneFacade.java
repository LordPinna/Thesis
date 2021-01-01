package it.blueface.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PhoneFacade {

	private EntityManagerFactory emf;
	private EntityManager em;

	public PhoneFacade() {
		this.emf = Persistence.createEntityManagerFactory("thesis-unit");
		this.em = emf.createEntityManager();
	}

	public Phone addPhone(String name, String extension, String ip, String port, String username) {
		Phone phone = new Phone(name, extension, ip, port);
		Customer customer = em.find(Customer.class, username);
		phone.setCustomer(customer);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(phone);
		tx.commit();
		em.close();
		emf.close();
		return phone;
	}

	public List<Phone> getPhonesByCustomer(String username) {
		Customer customer = em.find(Customer.class, username);
		Query query = em.createQuery("SELECT p FROM Phone p WHERE p.customer = :customer ORDER BY p.extension") ;
		query.setParameter("customer", customer);
		List<Phone> phones = (List<Phone>) query.getResultList();
		em.close();
		emf.close();
		return phones;
	}

	public List<Phone> getAllPhonesExcept(String extension) {
		Query query = em.createQuery("SELECT p FROM Phone p WHERE p.extension <> :extension");
		query.setParameter("extension", extension);
		List<Phone> phones = query.getResultList();
		em.close();
		emf.close();
		return phones;
	}

	public Phone getPhone(String extension) {
		Phone phone = em.find(Phone.class, extension);
		em.close();
		emf.close();
		return phone;
	}

}
