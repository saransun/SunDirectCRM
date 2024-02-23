package com.sundirect.crm.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.service.SubscriberService;

@RestController
@RequestMapping(value = "/sms/")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	SubscriberService subsService;
	
	
	@GetMapping(value="subscriber/info")
	public String customerInfo(Model model,@RequestParam(value = "userId", required = false) Optional<Integer> userId, 
			@RequestParam(value = "mobileNo", required = false) Optional<Long> mobileNo, 
			@RequestParam(value="smc", required = false) Optional<String> smc, @RequestParam(value="requestType") Optional<String> requestType) {
		
		String inp="";
		
		
		if(requestType.get().equalsIgnoreCase("UserID")) {
			 inp =String.valueOf(userId.get());
		}else if(requestType.get().equalsIgnoreCase("MobileNo")) {
			 inp =String.valueOf(mobileNo.get());
		}else if(requestType.get().equalsIgnoreCase("SMC")){
			 inp =String.valueOf(smc.get());
		}else {
			return "";
		}
		
		MyplexUserUser user=new MyplexUserUser();
		
		user=subsService.findUserInformation(inp,requestType.get());
		log.info("UserName: {}",user.getFirst());
		log.info("SMC: {}",user.getSmc());
		log.info("Mobile No: {}",user.getMobileNo());
		
		model.addAttribute(user);
		return "subscriber";
		
	}

}
