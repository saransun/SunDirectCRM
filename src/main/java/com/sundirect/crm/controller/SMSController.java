package com.sundirect.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.apientity.PlayerEventsPlayerevent;
import com.sundirect.crm.service.SubscriberService;
import com.sundirect.crm.smsentity.Subscription;

@Controller
//@RequestMapping(value = "/sms/")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	SubscriberService subsService;
	
	
	@GetMapping(value="/sms/subscriber/info")
	public String customerInfo(Model model,@RequestParam(value = "query", required = true) Optional<String> query, @RequestParam(value="requestType") Optional<String> requestType) {
		Integer userId=0;
		if(query.isPresent() && requestType.isPresent() && !query.get().isEmpty() ) {
			log.info("Entering into impl=====22222");
		String inp="";		
		if(requestType.get().equalsIgnoreCase("UserID")) {
			 inp =String.valueOf(query.get());
		}else if(requestType.get().equalsIgnoreCase("MobileNo")) {
			 inp =String.valueOf(query.get());
		}else if(requestType.get().equalsIgnoreCase("SMC")){
			 inp =String.valueOf(query.get());
		}		
		try {
			
			//MyplexUserUser user=new MyplexUserUser();
			
			List<MyplexUserUser> userList= new ArrayList<MyplexUserUser>();
			userList=subsService.findUserList(inp, requestType.get());		
			//model.addAttribute("userList",userList);
			//userId=user.getId();
				/*
				 * log.info("UserName: {}",user.getFirst()); log.info("SMC: {}",user.getSmc());
				 * log.info("Mobile No: {}",user.getMobileNo());
				 */
		model.addAttribute("user", userList);
		model.addAttribute("query", String.valueOf(query.get()));
		model.addAttribute("filter",requestType.get());
		}
        catch (NoSuchElementException e) {
        	log.info("User not found: {}",e.getMessage());
        	model.addAttribute("message", "User not found");
        }
		catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured: {}",e.getMessage());
			e.printStackTrace();
			model.addAttribute("message", "Please Enter Valid Input");
		}
		
		}
		else {
			model.addAttribute("message", "");
			
		}		
		return "subscriber";
	}
	
	@GetMapping(value="device")
	public String getCustomerDevice(Model model, @RequestParam(value="userId",required = true) Integer userId) {		
		/*
		 * List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
		 * deviceList=subsService.findDeviceInfoByUserId(userId);
		 * model.addAttribute("Device", deviceList); int inc=0;
		 */
		
		
		try {
			log.info("Entering in Device Details........");
			List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
			deviceList=subsService.findDeviceInfoByUserId(userId);
			model.addAttribute("deviceList", deviceList);
			int inc=0;
			for(MyplexUserDevice my:deviceList) {
				inc++;
				log.info("{} -  Device",inc);
				log.info("Os: {}",my.getOs());
				log.info("Os version: {}",my.getOs_version());
				log.info("Device make: {}",my.getMake());
				log.info("Model: {}",my.getModel());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured in device info: ",e.getMessage());
			model.addAttribute("message", "No Data available");
		}
		
		return "device";
	}
	
	@GetMapping(value="subscriptionInfo")
	public String getCustomerSubscription(Model model, @RequestParam(value="userId",required = true) Integer userId) {
		
		try {
			log.info("Entering in Subscription Details........");
			List<Subscription> activeSubscriptionList=new ArrayList<Subscription>();
			List<Subscription> expiredSubscriptionList=new ArrayList<Subscription>();
			activeSubscriptionList=subsService.findSubscriptionByuserId(userId);
			if(activeSubscriptionList.isEmpty()) {
				log.info("No Active subscription........");
				model.addAttribute("message1", "No Active subscription available");
			}
			for(Subscription s:activeSubscriptionList) {
				log.info("Active plan information: {}",s.getPlan().getName());
			}
			model.addAttribute("Active",activeSubscriptionList);	
			expiredSubscriptionList=subsService.findExpiredSubscriptionByuserId(userId);				
			if(expiredSubscriptionList.isEmpty()) {
				log.info("No expired subscription........");
				model.addAttribute("message1", "No Expired subscription available");
			}
			for(Subscription s:expiredSubscriptionList) {
				log.info("Expired plan information: {}",s.getPlan().getName());
			}
			model.addAttribute("Expired",expiredSubscriptionList);
		}catch(Exception e) {
			log.info("Exception occured in device info: ",e.getMessage());
			model.addAttribute("message", "No subscription available");
		}
				
		return "subscription";	
	}
	
	
	@GetMapping(value="otherInfo")
	public String getCustomerOtherInfo(Model model, @RequestParam(value="userId",required = true) Integer userId) {
		
		try {		
		List<PlayerEventsPlayerevent> eventList=subsService.findPlayerevents(userId);
		model.addAttribute("",eventList);
		
		for(PlayerEventsPlayerevent p:eventList) {
			log.info("Last 7 days Event List");
			log.info("");
		}
		
		}
		catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured in device info: ",e.getMessage());
			model.addAttribute("message", "No Player Event available");
		}
		
		
		return "otherInfo";
	}
		
	

}