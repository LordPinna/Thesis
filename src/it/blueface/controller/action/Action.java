package it.blueface.controller.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface Action {

	public String perform(HttpServletRequest request) throws IOException;
		

}
