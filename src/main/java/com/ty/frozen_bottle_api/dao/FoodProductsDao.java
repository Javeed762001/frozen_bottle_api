package com.ty.frozen_bottle_api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.repository.FoodProductsRepository;

@Repository
public class FoodProductsDao {
	
	
	@Autowired
	private FoodProductsRepository foodProductsRepository;
	
	public FoodProducts saveFoodProducts(FoodProducts foodProducts)
	{
		return foodProductsRepository.save(foodProducts);
	}
	
	

}
