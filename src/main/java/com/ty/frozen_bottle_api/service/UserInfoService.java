package com.ty.frozen_bottle_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.frozen_bottle_api.dto.*;
import com.ty.frozen_bottle_api.dao.UserInfoDao;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
	public ResponseEntity<ResponseStructure<UserInfo>> saveManager()
	{

		UserInfo recievedUserInfo=userInfoDao.saveManager();
		
		ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
		responseStucture.setStatusCode(HttpStatus.CREATED.value());
		responseStucture.setMessage("success , Added Manager");
		responseStucture.setData(recievedUserInfo);
		
		return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<UserInfo>> saveStaff(UserInfo userInfo)
	{

        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
		
		if(managerlist.size()!=0)
		{
			userInfo.setRole(Role.STAFF);
			UserInfo recievedUserInfo=userInfoDao.saveStaff(userInfo);
			
			ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success ,Staff Added");
			responseStucture.setData(recievedUserInfo);
			
			return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.CREATED);
		}
		else
		{
			ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, First Add Manager");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<UserInfo>> saveCustomer(UserInfo userInfo)
	{

        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
		
		if(managerlist.size()!=0)
		{
			userInfo.setRole(Role.CUSTOMER);
			UserInfo recievedUserInfo=userInfoDao.saveCustomer(userInfo);
			
			ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success ,Staff Added");
			responseStucture.setData(recievedUserInfo);
			
			return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.CREATED);
		}
		else
		{
			ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, First Add Manager");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	}

}
