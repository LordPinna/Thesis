package it.blueface.controller.action;

import it.blueface.model.Phone;
import it.blueface.model.PhoneFacade;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AddUser implements Action {

	public String perform(HttpServletRequest request) {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.0.101");
			factory.setUsername("guest");
			factory.setPassword("admin");

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			String extension = request.getParameter("extension");
			String confname = request.getParameter("confname");
			String pin = request.getParameter("pin");
			
			List<Phone> phonesin = (List<Phone>) request.getSession().getAttribute("phonesin");
			List<Phone> phonesout = (List<Phone>) request.getSession().getAttribute("phonesout");

			PhoneFacade pfacade = new PhoneFacade();
			Phone phone = pfacade.getPhone(extension);

			String message = "conference "+ confname + " dial sofia/internal/" + phone.getExtension() +
					"@" + phone.getIp() + ":" + phone.getPort();		
			channel.basicPublish("TAP.Commands", "commandBindingKey", null, message.getBytes());

			channel.close();
			connection.close();
			
			phonesin.add(phone);
			phonesout.remove(phone);

			request.setAttribute("confname", confname);
			request.setAttribute("pin", pin);
			request.getSession().setAttribute("phonesin", phonesin);
			request.getSession().setAttribute("phonesout", phonesout);
		}
		catch (IOException | TimeoutException e) {
			return "/newConference.jsp";
		}
		return "/newConference.jsp";
	}
}

