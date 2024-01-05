package com.ty.frozen_bottle_api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.Menu;
import com.ty.frozen_bottle_api.repository.FoodProductsRepository;
import com.ty.frozen_bottle_api.repository.MenuRepository;

@Repository
public class MenuDao {

	@Autowired
	private FoodProductsRepository foodProductsRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	public Menu saveMenu(Menu menu)
	{
		return menuRepository.save(menu);
	}

	public Menu UpadteMenu(Menu menu) {
		
		return menuRepository.save(menu);
	}
}
