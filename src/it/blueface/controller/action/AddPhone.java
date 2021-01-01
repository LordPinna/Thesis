package it.blueface.controller.action;

import it.blueface.model.Customer;
import it.blueface.model.Phone;
import it.blueface.model.PhoneFacade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AddPhone implements Action {

	public String perform(HttpServletRequest request) {

		Customer customer = (Customer) request.getSession().getAttribute("customer");
		List<Phone> phones = (List<Phone>) request.getSession().getAttribute("phones");
		request.getSession().removeAttribute("phones");
		
		String name = request.getParameter("name");
		String extension = request.getParameter("extension");
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		PhoneFacade facade = new PhoneFacade();
		Phone phone = facade.addPhone(name, extension, ip, port, customer.getUsername());
		
		phones.add(phone);
		request.getSession().setAttribute("phones", phones);
		
		return "/customerHome.jsp";
	}
}

