package com.nj.shopping.domain.service;

import com.nj.shopping.domain.ShoppingCart;

public interface BillingCalculationService {
	
	public ShoppingCart calculateShoppingCartValue(ShoppingCart cart);

}
