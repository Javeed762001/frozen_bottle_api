package com.ty.frozen_bottle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.Menu;
import com.ty.frozen_bottle_api.service.FoodProductsService;
import com.ty.frozen_bottle_api.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	MenuService menuService;
	
	
	
	@PostMapping("/savemenu")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu)
	{
		return menuService.saveMenu(menu);
	}
	
	@PutMapping("/updatemenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> UpdateMenu(@PathVariable int id)
	{
		return menuService.UpdateMenu(id);
	}
	
	@PutMapping("/addfoodproductmenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> addFoodProductToMenu(@RequestBody FoodProducts foodProduct,@PathVariable int id)
	{
		return menuService.addFoodProductToMenu(foodProduct,id);
	}
	
	
}
