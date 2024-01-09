package com.ty.frozen_bottle_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.frozen_bottle_api.dao.FoodOrderDao;
import com.ty.frozen_bottle_api.dto.ResponseStructure;
import com.ty.frozen_bottle_api.entity.FoodOrder;
import com.ty.frozen_bottle_api.entity.Item;
import com.ty.frozen_bottle_api.entity.UserInfo;
import com.ty.frozen_bottle_api.repository.FoodOrderRepository;
import com.ty.frozen_bottle_api.repository.ItemRepository;
import com.ty.frozen_bottle_api.repository.UserInfoRepository;
import com.ty.frozen_bottle_api.util.Role;
import com.ty.frozen_bottle_api.util.Status;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao foodOrderDao;
	
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(int sid,int cid)
	{

        List<UserInfo> managerlist=userInfoRepository.checkManager(Role.MANAGER);
		
		if(managerlist.size()!=0)
		{
			Optional<UserInfo> user=userInfoRepository.findById(sid);
			if(user.isPresent())
			{
			  UserInfo staff=user.get();
			  if(staff.getRole()==Role.STAFF)
			  {
				  Optional<UserInfo> cstmr=userInfoRepository.findById(cid);  
				if(cstmr.isPresent())
				{
					UserInfo customer=cstmr.get();
					FoodOrder foodOrder=new FoodOrder();
					foodOrder.setCustomerName(customer.getName());
					foodOrder.setStatus(Status.ORDERED);
					
					List<Item> items=itemRepository.getAllItemsByCustomerId(cid);
					System.out.println("+++++++++++++++++++++++++++++++");
					System.out.println(items);
					foodOrder.setItems(items);
					
					double totalprice=0;
					for(int i=0;i<items.size();i++)
					{
						totalprice=totalprice+(Double)items.get(i).getPrice();
					}
					
					foodOrder.setTotalPrice(totalprice);
					
					FoodOrder recievedFoodOrder=foodOrderDao.saveFoodOrder(foodOrder);
					
					
					
					
			ResponseStructure<FoodOrder> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.CREATED.value());
			responseStucture.setMessage("success ,Placed Order");
			responseStucture.setData(recievedFoodOrder);
			
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStucture,HttpStatus.CREATED);
				}
				else
				{
					ResponseStructure<FoodOrder> responseStucture = new ResponseStructure<>();
					responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
					responseStucture.setMessage("Customer ID NOT found ");
					responseStucture.setData(null);
					
					return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStucture,HttpStatus.NOT_FOUND);
				}
				
				
			  }
			  else
			  {
				    ResponseStructure<FoodOrder> responseStucture = new ResponseStructure<>();
					responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
					responseStucture.setMessage("Customer ID NOT found ");
					responseStucture.setData(null);
					
					return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStucture,HttpStatus.NOT_FOUND);
			  }
			}
			else
			{
				ResponseStructure<FoodOrder> responseStucture = new ResponseStructure<>();
				responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStucture.setMessage("User ID NOT found ");
				responseStucture.setData(null);
				
				return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStucture,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			ResponseStructure<FoodOrder> responseStucture = new ResponseStructure<>();
			responseStucture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStucture.setMessage("Staff ID NOT FOUND ");
			responseStucture.setData(null);
			
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStucture,HttpStatus.NOT_FOUND);
		}
	}
}
