package com.sundirect.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.bean.OrderCreation;
import com.sundirect.crm.bean.OrderCreationAPI;
import com.sundirect.crm.service.APIService;
import com.sundirect.crm.utils.ApiReturn;

@RestController
public class APIController {
	private static final Logger log = LoggerFactory.getLogger(APIController.class);
	@Autowired
	APIService apiservice;	
	
	@PostMapping(value = "/api/v1/order", consumes = "application/json")	
	public JsonNode createOrderSMS(@Valid @RequestBody OrderCreation model, HttpServletRequest request) {
		
		try {
			String returnVal=apiservice.orderCreation(model);
		//	JSONObject jsonObj=new JSONObject(returnVal);	
			log.info("returnVal string: {}",returnVal);
			ObjectMapper objectMapper=new ObjectMapper();        
			JsonNode jsonNode= objectMapper.readTree(returnVal);
			log.info("json string: {}",jsonNode.toString());
			return jsonNode;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}",e.getMessage());
			return null;
		}
		
	}
	
	@PostMapping(value = "/api/v1/apiOrder", consumes = "application/json")
	public JsonNode createOrderAPI(@Valid @RequestBody OrderCreationAPI model,HttpServletRequest request) {
		try {
				String returnVal=apiservice.orderCreationAPI(model);
			//	JSONObject jsonObj=new JSONObject(returnVal);	
				log.info("returnVal string: {}",returnVal);
				ObjectMapper objectMapper=new ObjectMapper();        
				JsonNode jsonNode= objectMapper.readTree(returnVal);
				log.info("json string: {}",jsonNode.toString());
				return jsonNode;
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	@GetMapping(value="/api/user/subscription/{userId}/{country}")
	public JsonNode getUserSubscription(@PathVariable(value = "userId") Integer userId,@PathVariable(value="country") String country,HttpServletRequest request) {
		
		try {
			log.info("user Id: {} country: {}",userId,country);
			String returnVal=apiservice.getAllSubscription(userId,country);
		//	JSONObject jsonObj=new JSONObject(returnVal);			
			ObjectMapper objectMapper=new ObjectMapper();        
			JsonNode jsonNode= objectMapper.readTree(returnVal);
			log.info("json string: {}",jsonNode.toString());
			return jsonNode;
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}",e.getMessage());
			return null;
		}	
		
	}
	
}
