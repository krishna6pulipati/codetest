package com.interview.test.jms;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import com.interview.test.model.Product;
import com.interview.test.service.ProductService;
@Component
public class MessageReceiver {
	@Autowired
	ProductService productService;
	
	@JmsListener(destination = "shopping.cart.queue")
	public void receiveQueue(Product message) throws SQLException {		
		System.out.println("Message Received: "+message);
		productService.saveRetryService(message);
		
	}
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	}
}
