package com.nj.shopping;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nj.shopping.domain.ShoppingCart;
import com.nj.shopping.domain.service.BillingCalculationService;

class BillingCalculationServiceTests extends ShoppingcartApplicationTests{

	@Autowired
	BillingCalculationService billingCalculationService;
	
	@Test
	void testGetBillingfor5000() {
		
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular")
				.purchaseAmount(new BigDecimal("5000")).build();
		
		cart = billingCalculationService.calculateShoppingCartValue(cart);
		
		assertEquals(0 , cart.getBillingAmount().compareTo(new BigDecimal("5000")));
	}
	
	@Test
	void testGetBillingfor10000() {
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular")
				.purchaseAmount(new BigDecimal("10000")).build();
		
		cart = billingCalculationService.calculateShoppingCartValue(cart);
		
		assertEquals(0 , cart.getBillingAmount().compareTo(new BigDecimal("9500")));
	}
	
	@Test
	void testGetBillingfor15000() {
		ShoppingCart cart = ShoppingCart.builder().customerType("Regular")
				.purchaseAmount(new BigDecimal("15000")).build();
		
		cart = billingCalculationService.calculateShoppingCartValue(cart);
		
		assertEquals(0 , cart.getBillingAmount().compareTo(new BigDecimal("13500")));
	}

}
