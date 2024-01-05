package com.ty.frozen_bottle_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.frozen_bottle_api.dto.*;
import com.ty.frozen_bottle_api.dao.UserInfoDao;
import com.ty.frozen_bottle_api.entity.UserInfo;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	
	public ResponseEntity<ResponseStructure<UserInfo>> saveManager()
	{

		UserInfo recievedUserInfo=userInfoDao.saveManager();
		
		ResponseStructure<UserInfo> responseStucture = new ResponseStructure<>();
		responseStucture.setStatusCode(HttpStatus.CREATED.value());
		responseStucture.setMessage("success , Added Manager");
		responseStucture.setData(recievedUserInfo);
		
		return new ResponseEntity<ResponseStructure<UserInfo>>(responseStucture,HttpStatus.CREATED);
	}

}
