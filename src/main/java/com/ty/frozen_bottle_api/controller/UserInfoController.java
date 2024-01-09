package com.ty.frozen_bottle_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.service.UserInfoService;

@RestController
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@PostMapping("/savemanager")
	public ResponseEntity<ResponseStructure<UserInfo>> saveManager()
	{
		return userInfoService.saveManager();
	}
	
	@PostMapping("/savestaff")
	public ResponseEntity<ResponseStructure<UserInfo>> saveStaff(@RequestBody UserInfo userInfo)
	{
		return userInfoService.saveStaff(userInfo);
	}
	
	@PostMapping("/savecustomer")
	public ResponseEntity<ResponseStructure<UserInfo>> saveCustomer(@RequestBody UserInfo userInfo)
	{
		return userInfoService.saveCustomer(userInfo);
	}

}
