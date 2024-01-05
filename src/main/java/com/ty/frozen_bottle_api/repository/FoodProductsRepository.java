package com.ty.frozen_bottle_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.frozen_bottle_api.entity.FoodProducts;

public interface FoodProductsRepository extends JpaRepository<FoodProducts, Integer>{
	

}
