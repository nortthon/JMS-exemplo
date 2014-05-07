package pacote;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ConsumidorDeMensagem
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test")
})
public class ConsumidorDeMensagem implements MessageListener {

    @Override
    public void onMessage(Message message) {
    	 try {
             TextMessage msg = (TextMessage) message;
             String txt = msg.getText();
             System.out.println("TextMessage received: " + txt.toString());
         } catch (JMSException e) {
             e.printStackTrace();
         }
    }

}
