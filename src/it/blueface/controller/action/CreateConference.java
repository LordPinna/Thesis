package it.blueface.controller.action;

import it.blueface.controller.helper.CreateConferenceHelper;
import it.blueface.model.CustomerFacade;
import it.blueface.model.Phone;
import it.blueface.model.PhoneFacade;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class CreateConference implements Action{

	public String perform(HttpServletRequest request) {

		CreateConferenceHelper helper = new CreateConferenceHelper();

		if(helper.isValid(request)) {

			try {
				ConnectionFactory factory = new ConnectionFactory();
				factory.setHost("192.168.0.101");
				factory.setUsername("guest");
				factory.setPassword("admin");

				Connection connection = factory.newConnection();
				Channel channel = connection.createChannel();

				String extension = request.getParameter("extension");
				String confname = request.getParameter("confname");

				PhoneFacade pfacade = new PhoneFacade();
				Phone phone = pfacade.getPhone(extension);

				pfacade = new PhoneFacade();
				List<Phone> phones = pfacade.getAllPhonesExcept(extension);

				String message = "conference " + confname + " dial sofia/internal/" + phone.getExtension() +
						"@" + phone.getIp() + ":" + phone.getPort();		
				channel.basicPublish("TAP.Commands", "commandBindingKey", null, message.getBytes());

				channel.close();
				connection.close();
				
				List<Phone> phonesin = new LinkedList<Phone>();
				List<Phone> phonesout = new LinkedList<Phone>(phones);
				phonesin.add(phone);

				request.getSession().setAttribute("phonesin", phonesin);
				request.getSession().setAttribute("phonesout", phonesout);
				request.setAttribute("confname", confname);
			}
			catch (IOException | TimeoutException e) {
				return "/customerHome.jsp.jsp";
			}
			return "/newConference.jsp";
		} else return "/customerHome.jsp.jsp";
	}
}

