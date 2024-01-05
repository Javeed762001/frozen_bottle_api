package com.ty.frozen_bottle_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.frozen_bottle_api.entity.*;
import com.ty.frozen_bottle_api.dao.FoodProductsDao;
import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;

@Service
public class FoodProductsService {
	
	@Autowired
	private FoodProductsDao foodProductsDao;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public ResponseEntity<ResponseStructure<FoodProducts>> saveFoodProducts(FoodProducts foodProduct)
	{

        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
        System.out.println(managerlist.size());
		
		if(managerlist.size()!=0)
		{
			FoodProducts recievedFoodProduct=foodProductsDao.saveFoodProducts(foodProduct);
			
			ResponseStructure<FoodProducts> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success , FoodProduct Added");
			responseStucture.setData(foodProduct);
			
			return new ResponseEntity<ResponseStructure<FoodProducts>>(responseStucture,HttpStatus.CREATED);
		}
		else
		{
			ResponseStructure<FoodProducts> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, First Add Manager");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<FoodProducts>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	}

}
