package me.rami;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class monServeur {
	
    
	Session session;

	@Autowired
	coreJMSlmg ConfJMS;
	
	@Autowired
	coreJMSListenerLmg TestListener;

    @PostConstruct
    public void init(){
        // start your monitoring in here
		Session session;
		
		try {
	    	SqsQueue Q = ConfJMS.getQueue();
			session = ConfJMS.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("QueueR");
	        MessageConsumer consumer = session.createConsumer(queue);
	        consumer.setMessageListener(TestListener);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JMS Message " );
    	
    	
    	
    	
    }

	

}