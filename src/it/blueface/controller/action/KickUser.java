package it.blueface.controller.action;

import it.blueface.model.Phone;
import it.blueface.model.PhoneFacade;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.freeswitch.esl.ESLconnection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class KickUser implements Action {

	public String perform(HttpServletRequest request) throws IOException{

		try {

			System.load("/home/lordpinna/libesljni.so");

		} catch (UnsatisfiedLinkError ex){ }
		try {

			ESLconnection con = new ESLconnection("192.168.0.101","8021","ClueCon");
			con.events("plain","all");

			String extension = request.getParameter("extension");
			String confname = request.getParameter("confname");

			List<Phone> phonesin = (List<Phone>) request.getSession().getAttribute("phonesin");
			List<Phone> phonesout = (List<Phone>) request.getSession().getAttribute("phonesout");


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

					String message = "conference " + confname + " kick " + id;
					channel.basicPublish("TAP.Commands", "commandBindingKey", null, message.getBytes());

					channel.close();
					connection.close();
				}

			}

			scanner.close();

			PhoneFacade pfacade = new PhoneFacade();
			Phone phone = pfacade.getPhone(extension);
			phonesin.remove(phone);
			phonesout.add(phone);


			request.getSession().setAttribute("phonesin", phonesin);
			request.getSession().setAttribute("phonesout", phonesout);
			request.setAttribute("confname", confname);

		} catch (IOException | TimeoutException e) {
			return "/newConference.jsp";
		}

		return "/newConference.jsp";
	}
}
