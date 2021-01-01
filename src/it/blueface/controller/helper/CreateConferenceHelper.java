package it.blueface.controller.helper;

import it.blueface.model.Customer;

import javax.servlet.http.HttpServletRequest;

public class CreateConferenceHelper {

	public boolean isValid(HttpServletRequest request){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		double credit = customer.getCredit();
		if (credit < 1){
			request.setAttribute("noCredit", "You cannot create a new conference because your credit is too low");
			return false;
		}
		return true;
	}

}
