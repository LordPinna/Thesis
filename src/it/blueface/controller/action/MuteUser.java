package it.blueface.controller.action;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.freeswitch.esl.ESLconnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MuteUser implements Action{

	public String perform(HttpServletRequest request) {

		try {

			System.load("/home/lordpinna/libesljni.so");

		} catch (UnsatisfiedLinkError ex){ }
		try {

			ESLconnection con = new ESLconnection("192.168.0.101","8021","ClueCon");
			con.events("plain","all");

			String extension = request.getParameter("extension");
			String confname = request.getParameter("confname");

			String conflist = con.sendRecv("api conference "+ confname + " list").getBody();
			con.disconnect();
			
			Scanner scanner = new Scanner(conflist);
			Scanner scannerline;
			String line;
			boolean found = false;
			while (scanner.hasNextLine() && !found){
				line = scanner.nextLine();
				scannerline = new Scanner(line).useDelimiter(";");
				String id = scannerline.next();	
				scannerline.next();
				scannerline.next();
				scannerline.next();
				String callerid = scannerline.next();
				if (callerid.equals(extension)){
					found = true;
					ConnectionFactory factory = new ConnectionFactory();
					factory.setHost("192.168.0.101");
					factory.setUsername("guest");
					factory.setPassword("admin");

					Connection connection = factory.newConnection();
					Channel channel = connection.createChannel();
					String message = "conference " + confname + " mute " + id;
					channel.basicPublish("TAP.Commands", "commandBindingKey", null, message.getBytes());

					channel.close();
					connection.close();

					request.setAttribute("confname", confname);
				}
			}
			scanner.close();
		}
		catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	return "/newConference.jsp";
}
}