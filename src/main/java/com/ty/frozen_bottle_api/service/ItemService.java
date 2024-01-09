package com.ty.frozen_bottle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.frozen_bottle_api.dao.ItemDao;
import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodProducts;
import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.FoodProductsRepository;
import com.ty.frozen_bottle_api.repository.ItemRepository;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private FoodProductsRepository foodProductsRepository;
	
	public ResponseEntity<ResponseStructure<Item>> saveItem(Item item,int id, int cid)
	{

        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
		
		if(managerlist.size()!=0)
		{
			Optional<UserInfo> user=userInfoRepository.findById(id);
			if(user.isPresent())
			{
			  UserInfo staff=user.get();
			  if(staff.getRole()==Role.STAFF)
			  {
				  Optional<UserInfo> cstmr=userInfoRepository.findById(cid);  
				if(cstmr.isPresent())
				{
					UserInfo customer=cstmr.get();
					item.setUser(customer);
			Optional<FoodProducts> fp=foodProductsRepository.findById(item.getProductId());
			if(fp.isPresent())
			{
				FoodProducts foodProduct=fp.get();
				double price=foodProduct.getPrice();
				
				int quantity=item.getQuantity();
				
				double qprice=price*quantity;
				item.setPrice(qprice);
				
				Item recievedItem=itemDao.saveItem(item);
				
				ResponseStructure<Item> responseStucture = new ResponseStructure<>();
				responseStucture.setStatusCode(HttpStatus.CREATED.value());
				responseStucture.setMessage("success ,Item Added");
				responseStucture.setData(recievedItem);
				
				return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.CREATED);
			}
			else
			{
				ResponseStructure<Item> responseStucture = new ResponseStructure<>();
				responseStucture.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseStucture.setMessage("FoodProduct Not Found, Failed to Add Item");
				responseStucture.setData(null);
				
				return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.BAD_REQUEST);
			}
			}
				else
				{
					ResponseStructure<Item> responseStucture = new ResponseStructure<>();
					responseStucture.setStatusCode(HttpStatus.BAD_REQUEST.value());
					responseStucture.setMessage("Customer Not found");
					responseStucture.setData(null);
					
					return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.BAD_REQUEST);
				}
			}
			  else
			  {
				  ResponseStructure<Item> responseStucture = new ResponseStructure<>();
					responseStucture.setStatusCode(HttpStatus.BAD_REQUEST.value());
					responseStucture.setMessage("YOU ARE NOT STAFF");
					responseStucture.setData(null);
					
					return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.BAD_REQUEST);

			  }
		}
		else
		{
			ResponseStructure<Item> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStucture.setMessage("USER ID Not Found, Failed to Add Item");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.BAD_REQUEST);
		}
		}	
		else
		{
			ResponseStructure<Item> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Failed, First Add Manager");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<Item>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	}
}
