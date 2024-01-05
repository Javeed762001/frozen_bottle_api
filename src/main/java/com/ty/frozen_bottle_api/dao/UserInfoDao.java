package com.ty.frozen_bottle_api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;

@Repository
public class UserInfoDao {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public UserInfo saveManager()
	{
		List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
		
		if(managerlist.isEmpty())
		{
		  UserInfo manager=new UserInfo();
		  manager.setName("ram");
		  manager.setEmail("ram@gmail.com");
		  manager.setPassword("Ram@123");
		  manager.setRole(Role.MANAGER);
		System.out.println("Manager Created Successfuy................");
		return userInfoRepository.save(manager);
		}
		else
		{
			System.out.println("Manager already exist");
			return null;
		}
		
	}
}
