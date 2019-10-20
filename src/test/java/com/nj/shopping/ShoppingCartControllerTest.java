package com.nj.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nj.shopping.domain.ShoppingCart;

public class ShoppingCartControllerTest extends ShoppingcartApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testGetShoppingCartfor5000() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/getBillingAmount";
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular").purchaseAmount(new BigDecimal("5000"))
				.build();

		ResponseEntity<ShoppingCart> response = restTemplate.postForEntity(baseUrl, cart, ShoppingCart.class);

		ShoppingCart shoppingCart = response.getBody();
		assertEquals(new BigDecimal("5000"), shoppingCart.getBillingAmount());

	}
	
	@Test
	public void testGetShoppingCartfor10000() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/getBillingAmount";
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular").purchaseAmount(new BigDecimal("10000"))
				.build();

		ResponseEntity<ShoppingCart> response = restTemplate.postForEntity(baseUrl, cart, ShoppingCart.class);

		ShoppingCart shoppingCart = response.getBody();
		assertEquals(0, new BigDecimal("9500").compareTo(shoppingCart.getBillingAmount()));

	}
	
	@Test
	public void testGetShoppingCartfor15000() {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/getBillingAmount";
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular").purchaseAmount(new BigDecimal("15000"))
				.build();

		ResponseEntity<ShoppingCart> response = restTemplate.postForEntity(baseUrl, cart, ShoppingCart.class);

		ShoppingCart shoppingCart = response.getBody();
		System.out.println(shoppingCart.toString());
		assertEquals(0 , new BigDecimal("13500").compareTo(shoppingCart.getBillingAmount()));

	}
	
	

}
