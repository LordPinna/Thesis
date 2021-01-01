package it.blueface.controller.action;


import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import org.freeswitch.esl.ESLconnection;
import org.freeswitch.esl.ESLevent;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SetPin implements Action{

	public String perform(HttpServletRequest request) {

		final String EXCHANGE_NAME = "logs";
		try {
			System.load("/home/agostino/Desktop/Blueface Project/lib/libesljni.so");
		} catch (UnsatisfiedLinkError ex){ }

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.16.16.34");
		factory.setUsername("asterisk");
		factory.setPassword("prova");
		Connection connection;

		try {
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			ESLconnection con = new ESLconnection("127.0.0.1","8021","ClueCon");
			ESLevent evt = null;
			con.events("plain","all");

			String confname = request.getParameter("confname");
			String pin = request.getParameter("pin");

			evt = con.sendRecv("api conference " + confname + " pin " + pin);		
			String event = evt.serialize("plain");
			channel.basicPublish(EXCHANGE_NAME, "", null, event.getBytes());

			channel.close();
			connection.close();

			request.setAttribute("confname", confname);
			request.setAttribute("pin", pin);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "/newConference.jsp";
	}
}
