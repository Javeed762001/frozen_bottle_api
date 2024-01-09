package com.ty.frozen_bottle_api.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.frozen_bottle_api.util.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Status status;
	double totalPrice;
	
	@CreationTimestamp
	private LocalDateTime orderCreatedTime;
	
	@UpdateTimestamp
	private LocalDateTime orderDeliveryTime;
	
	private String customerName;
	
	@JsonIgnore
	@OneToMany
	List<Item> items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDateTime getOrderCreatedTime() {
		return orderCreatedTime;
	}

	public void setOrderCreatedTime(LocalDateTime orderCreatedTime) {
		this.orderCreatedTime = orderCreatedTime;
	}

	public LocalDateTime getOrderDeliveryTime() {
		return orderDeliveryTime;
	}

	public void setOrderDeliveryTime(LocalDateTime orderDeliveryTime) {
		this.orderDeliveryTime = orderDeliveryTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
