package com.ty.frozen_bottle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.service.FoodProductsService;

@RestController
public class FoodProductController {

	@Autowired
	FoodProductsService foodProductsService;
	
	@PostMapping("/savefoodproduct")
	public ResponseEntity<ResponseStructure<FoodProducts>> saveFoodProduct(@RequestBody FoodProducts foodProducts)
	{
		return foodProductsService.saveFoodProducts(foodProducts);
	}
}
