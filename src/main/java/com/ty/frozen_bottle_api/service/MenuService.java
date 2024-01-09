package com.ty.frozen_bottle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.frozen_bottle_api.dao.FoodProductsDao;
import com.ty.frozen_bottle_api.dao.MenuDao;
import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.Menu;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.FoodProductsRepository;
import com.ty.frozen_bottle_api.repository.MenuRepository;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;


@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private FoodProductsRepository foodProductsRepository;
	
	@Autowired
	private FoodProductsDao foodProductsDao;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu,String email,String password)
	{

		UserInfo userInfo=userInfoRepository.validateManager(email, password);
        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
       
        if(userInfo!=null)
        {
		if(managerlist.size()!=0)
		{
			
			Menu recievedFoodMenu=menuDao.saveMenu(menu);
			
			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success , Menu Created");
			responseStucture.setData(recievedFoodMenu);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.CREATED);
		}
		else
		{
			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, to create menu");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
		}
        }
        else
        {
        	ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("YOU ARE NOT MANAGER");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
        }
	}

	public ResponseEntity<ResponseStructure<Menu>> UpdateMenu(int id,String email,String password) {
		
		UserInfo userInfo=userInfoRepository.validateManager(email, password);
		List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
	     
	   if(userInfo!=null)
	   {
		if(managerlist.size()!=0)
		{
			Optional<Menu> m=menuRepository.findById(id);
			Menu menu=m.get();
			
			List<FoodProducts> fprods=foodProductsRepository.findAll();
			List<FoodProducts> foodprods=menu.getFoodProducts();
			foodprods.addAll(fprods);
			
			menu.setFoodProducts(foodprods);
			Menu recievedFoodMenu=menuDao.UpadteMenu(menu);
			
			for(int i=0;i<fprods.size();i++)
			{
				FoodProducts fp= fprods.get(i);
				fp.setMenu(menu);
				foodProductsDao.saveFoodProducts(fp);
			}
			
			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success , Menu Updated");
			responseStucture.setData(recievedFoodMenu);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.CREATED);
		}
		else
		{
			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, to Update menu");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	   }
	   else
       {
       	ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("YOU ARE NOT MANAGER");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
       }
	   
	}


	public ResponseEntity<ResponseStructure<Menu>> addFoodProductToMenu(FoodProducts foodProduct, int id, String email,String password) {
		
		UserInfo userInfo=userInfoRepository.validateManager(email, password);
		List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
	      
	  if(userInfo!=null)
	 {
		if(managerlist.size()!=0)
		{
			
			Optional<Menu> m=menuRepository.findById(id);
			Menu menu=m.get();
			
			foodProduct.setMenu(menu);
			foodProductsDao.saveFoodProducts(foodProduct);
			
			List<FoodProducts> foodprods=menu.getFoodProducts();
			foodprods.add(foodProduct);
			menu.setFoodProducts(foodprods);
			Menu recievedFoodMenu=menuDao.UpadteMenu(menu);
			
			
			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success , Added foodProduct to menu");
			responseStucture.setData(recievedFoodMenu);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.CREATED);	
			
		}
		else
		{

			ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, to Add Product to menu");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	
	}
	  else
      {
      	ResponseStructure<Menu> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("YOU ARE NOT MANAGER");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Menu>>(responseStucture,HttpStatus.NOT_FOUND);
      }
	}
	  
}
