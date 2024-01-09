package com.ty.frozen_bottle_api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.ItemRepository;

@Repository
public class ItemDao {

	@Autowired
	private ItemRepository itemRepository;
	
	public Item saveItem(Item item)
	{
		return itemRepository.save(item);
	}
}
