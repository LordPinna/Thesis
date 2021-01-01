package it.blueface.controller.action;

import it.blueface.model.Customer;
import it.blueface.model.CustomerFacade;
import javax.servlet.http.HttpServletRequest;

public class CreateCustomer implements Action {

	public String perform(HttpServletRequest request) {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		CustomerFacade facade = new CustomerFacade();
		Customer customer = facade.createCustomer(username, password, name, surname, email);
		request.setAttribute("customer", customer);

		return "/newCustomer.jsp";
	}
}
