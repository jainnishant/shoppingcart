package com.nj.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nj.shopping.domain.DiscountSlab;

@Repository
public interface DiscountSlabRepository extends JpaRepository<DiscountSlab, Long> {

}
