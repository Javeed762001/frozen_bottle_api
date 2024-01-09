package com.ty.frozen_bottle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodOrder;
import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.service.FoodOrderService;
import com.ty.frozen_bottle_api.service.FoodProductsService;

@RestController
public class FoodOrderController {

	@Autowired
	private FoodOrderService foodOrderService;
	
	@PostMapping("/savefoodorder/staffid/{id}/customerid/{cid}")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveItem(@PathVariable int id,@PathVariable int cid)
	{
		return foodOrderService.saveFoodOrder(id, cid);
	}
}
