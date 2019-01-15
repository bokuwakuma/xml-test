package jp.kuma.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class HelloWorldXmlApp {

	private static Logger logger = LoggerFactory.getLogger(HelloWorldXmlApp.class);
	
	public static void main(String... args) {
		try(AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("/META-INF/spring/spring-integration.xml", HelloWorldXmlApp.class)) {
			
			MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
			PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);			

			inputChannel.send(new GenericMessage<String>("Spring Integration"));
			logger.info(outputChannel.receive().getPayload().toString());
		}
	}
}
