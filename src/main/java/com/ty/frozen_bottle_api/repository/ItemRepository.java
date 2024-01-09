package com.ty.frozen_bottle_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.frozen_bottle_api.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query("SELECT p FROM Item p WHERE p.user.id=?1")
	public List<Item> getAllItemsByCustomerId(int id);
}
