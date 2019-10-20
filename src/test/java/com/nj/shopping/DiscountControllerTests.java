package com.nj.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.nj.shopping.Repository.DiscountSlabRepository;
import com.nj.shopping.domain.DiscountSlabs;

@RunWith(SpringRunner.class)
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
		assertEquals(true, discounts.getBody().contains("20.0"));
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
		DiscountSlabs discountSlab = DiscountSlabs.builder()
													.fromValue(12000)
													.toValue(15000)
													.discountPercentage(new BigDecimal("25.0"))
													.build();
		int precount = repo.findAll().size();
		restTemplate.postForObject(baseUrl, discountSlab, DiscountSlabs.class);
		int postCount = repo.findAll().size();
		assertEquals(precount + 1, postCount);
	}

}
