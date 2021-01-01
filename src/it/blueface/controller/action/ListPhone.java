package it.blueface.controller.action;

import java.util.List;

import it.blueface.model.Customer;
import it.blueface.model.Phone;
import it.blueface.model.PhoneFacade;

import javax.servlet.http.HttpServletRequest;

public class ListPhone implements Action {

	public String perform(HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		PhoneFacade facade = new PhoneFacade();
		List<Phone> phones = facade.getPhonesByCustomer(customer.getUsername());
		request.getSession().setAttribute("phones", phones);
		return "/phones.jsp";
	}
}



