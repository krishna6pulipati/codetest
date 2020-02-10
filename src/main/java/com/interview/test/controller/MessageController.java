package com.interview.test.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.test.model.Product;

@RestController
@RequestMapping("/api")
public class MessageController {
	@Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @PostMapping("message")
    public ResponseEntity<String> publish(@RequestBody Product message){
    	this.jmsMessagingTemplate.convertAndSend(this.queue, message);
		System.out.println("Message has been put to queue by sender");
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
