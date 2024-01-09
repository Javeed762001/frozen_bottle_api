package com.ty.frozen_bottle_api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.frozen_bottle_api.entity.FoodOrder;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.repository.FoodOrderRepository;
import com.ty.frozen_bottle_api.repository.FoodProductsRepository;
import com.ty.frozen_bottle_api.repository.ItemRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder)
	{
		return foodOrderRepository.save(foodOrder);
	}
}
