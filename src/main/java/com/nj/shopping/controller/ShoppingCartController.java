package com.nj.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nj.shopping.domain.ShoppingCart;
import com.nj.shopping.domain.service.BillingCalculationService;

@RestController
public class ShoppingCartController {
	@Autowired
	BillingCalculationService billingCalaculationService;

	@PostMapping("/getBillingAmount")
	public ResponseEntity<ShoppingCart> getShoppingCart(@RequestBody ShoppingCart cart) {

		cart = billingCalaculationService.calculateShoppingCartValue(cart);
		
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);

	}

}
