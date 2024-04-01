package com.sundirect.crm.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sundirect.crm.bean.OrderCreation;
import com.sundirect.crm.bean.OrderCreationAPI;

public interface APIService {

	String getAllPlanAPI(String status);
	
	String orderCreation(OrderCreation model);
	
	String orderCreationAPI(OrderCreationAPI model);
	
	String getAllSubscription(Integer userId, String country);
	
}
