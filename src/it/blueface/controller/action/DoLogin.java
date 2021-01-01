package it.blueface.controller.action;

import it.blueface.model.*;

import javax.servlet.http.HttpServletRequest;

public class DoLogin implements Action {

	public String perform(HttpServletRequest request) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		CustomerFacade cfacade = new CustomerFacade();
		Customer customer = cfacade.getCustomer(username, password);

		if(customer!=null) {
			request.getSession().setAttribute("customer", customer);
			request.getSession().setAttribute("phones", customer.getPhones());
			return "/customerHome.jsp";
		}
		else{
			request.setAttribute("loginErr", "Wrong username or password");
			return "/index.jsp";
		}
	}
}
