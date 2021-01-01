package it.blueface.controller.action;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class StopConference implements Action {

	public String perform(HttpServletRequest request){

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.0.101");
			factory.setUsername("guest");
			factory.setPassword("admin");

			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			String confname = request.getParameter("confname");
			String message = "conference "+ confname + " kick all";
			channel.basicPublish("TAP.Commands", "commandBindingKey", null, message.getBytes());

			channel.close();
			connection.close();
		}
		catch (IOException | TimeoutException e) {
			return "/newConference.jsp";
		}
		return "/customerHome.jsp";
	}
}
