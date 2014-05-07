package br.com.itau.internet;

import java.io.IOException;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientServletJMS
 */
public class ClientServletJMS extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			Context context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup("java:/ConnectionFactory");
            
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            
            
            Queue queue = (Queue)context.lookup("java:/queue/test");
            QueueSender sender = session.createSender(queue);
            
            
            for (int i = 0; i < 100; i++) {
            	//1. Sending TextMessage to the Queue 
                TextMessage message = session.createTextMessage();
                message.setText("Hello EJB3 MDB Queue de número " +i+ "!!!");
                sender.send(message);
			}
            
            sender.close();
            session.close();
            connection.close();
            context.close();
            
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
