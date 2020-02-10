package com.interview.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.test.model.Product;
import com.interview.test.repository.ProductRepository;


@SpringBootTest
@AutoConfigureMockMvc
class ShoppingCartMessageApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	protected MockMvc mvc;
	
	@MockBean
    private ProductRepository mockRepository;
	
	@Autowired
	WebApplicationContext webApplicationContext;

	@Test
	public void testPublishSuccess() throws JsonProcessingException, Exception {
		   Product product = new Product();
		   product.setName("Test Product 1123");
		   when(mockRepository.save(product)).thenReturn(product);
		   ObjectMapper objectMapper = new ObjectMapper();
		   mvc.perform(post("/api/message")
				   .content(objectMapper.writeValueAsString(product))
				   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				   .andExpect(status().is2xxSuccessful());
		   verify(mockRepository, times(1)).save(product);
		   
	}
}
