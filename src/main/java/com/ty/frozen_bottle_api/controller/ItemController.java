package com.ty.frozen_bottle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.entity.Menu;
import com.ty.frozen_bottle_api.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@PostMapping("/saveitem/staffid/{id}/customerid/{cid}")
	public ResponseEntity<ResponseStructure<Item>> saveItem(@RequestBody Item item,@PathVariable int id,@PathVariable int cid)
	{
		return itemService.saveItem(item, id ,cid);
	}
}
