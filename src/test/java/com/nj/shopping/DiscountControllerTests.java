package com.nj.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.nj.shopping.Repository.DiscountSlabRepository;
import com.nj.shopping.domain.DiscountSlab;

class DiscountControllerTests extends ShoppingcartApplicationTests{

	@Autowired
	DiscountSlabRepository repo;
	@LocalServerPort
	int randomServerPort;

	@Test
	void testGetAllDiscounts() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl  = "http://localhost:" + randomServerPort + "/discounts";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> discounts = restTemplate.getForEntity(uri, String.class);
		assertEquals(200, discounts.getStatusCodeValue());
		assertEquals(true, discounts.getBody().contains("10"));
	}
	
	@Test
	void deleteDiscounts() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl  = "http://localhost:" + randomServerPort + "/discounts/{id}";
		int precount = repo.findAll().size();
		Map<String, String> params = new HashMap<String,String>();
		params.put("id", "1");
		restTemplate.delete(baseUrl,params);
		int postCount = repo.findAll().size();
		assertEquals(precount -1, postCount);
	}
	
	@Test
	void addDiscounts() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl  = "http://localhost:" + randomServerPort + "/discounts";
		Map<String, String> params = new HashMap<String,String>();
		DiscountSlab discountSlab = DiscountSlab.builder()
													.fromValue(new BigDecimal("18000"))
													.toValue(new BigDecimal("20000"))
													.discountPercentage(new BigDecimal("25.0"))
													.build();
		int precount = repo.findAll().size();
		restTemplate.postForObject(baseUrl, discountSlab, DiscountSlab.class);
		int postCount = repo.findAll().size();
		assertEquals(precount + 1, postCount);
	}

}
