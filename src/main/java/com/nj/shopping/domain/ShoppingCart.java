package com.nj.shopping.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCart {

	String customerType;
	BigDecimal purchaseAmount;
	BigDecimal discountApplied;
	BigDecimal billingAmount;
	
}
