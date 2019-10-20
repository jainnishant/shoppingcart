package com.nj.shopping.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj.shopping.Repository.DiscountSlabRepository;
import com.nj.shopping.domain.DiscountSlab;
import com.nj.shopping.domain.ShoppingCart;
import com.nj.shopping.domain.service.BillingCalculationService;

@Service
public class BillingCalculationServiceImpl implements BillingCalculationService {

	@Autowired
	DiscountSlabRepository repository;

	@Override
	public ShoppingCart calculateShoppingCartValue(ShoppingCart cart) {

		List<DiscountSlab> discountSlabs = repository.findAll();

		BigDecimal discountPercent = null;

		BigDecimal ONE_HUNDRED = new BigDecimal("100");

		for (DiscountSlab slab : discountSlabs) {
			if ((slab.getToValue() != null) && (slab.getFromValue().compareTo(cart.getPurchaseAmount()) < 0
					&& cart.getPurchaseAmount().compareTo(slab.getToValue()) <= 0)) {
				discountPercent = slab.getDiscountPercentage();
			} else if ((slab.getToValue() == null) && (slab.getFromValue().compareTo(cart.getPurchaseAmount()) < 0)) {
				discountPercent = slab.getDiscountPercentage();
			}
		}

		if (discountPercent == null || discountPercent.equals(new BigDecimal("0.0"))) {
			cart.setBillingAmount(cart.getPurchaseAmount());
		} else {
			BigDecimal percentageApplied = ONE_HUNDRED.subtract(discountPercent);
			cart.setDiscountApplied(discountPercent);
			cart.setBillingAmount(cart.getPurchaseAmount().multiply(percentageApplied).divide(ONE_HUNDRED));
		}

		return cart;

	}

}
