package com.nj.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nj.shopping.Repository.DiscountSlabRepository;
import com.nj.shopping.domain.DiscountSlab;

@RestController
public class DiscountController {

	@Autowired
	DiscountSlabRepository discountSlabRepository;

	@GetMapping("/discounts")
	public List<DiscountSlab> getAllDiscounts() {
		return discountSlabRepository.findAll();
	}

	@DeleteMapping("/discounts/{id}")
	public void deletDiscount(@PathVariable long id) {
		discountSlabRepository.deleteById(id);
	}

	@PostMapping("/discounts")
	public ResponseEntity<Object> createDiscount(@RequestBody DiscountSlab discountSlabs) {
		discountSlabRepository.save(discountSlabs);
		return new ResponseEntity(HttpStatus.CREATED);
	}

}
