package com.sundirect.crm.controller;

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

import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.service.SubscriberService;

@Controller
//@RequestMapping(value = "/sms/")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	SubscriberService subsService;
	
	
	@GetMapping(value="/subscriber/info")
	public String customerInfo(Model model,@RequestParam(value = "query", required = false) Optional<String >query, 
			 @RequestParam(value="requestType",required = false) Optional<String> requestType) {
	

		String inp="";
		//String message="";
		//inp="9912720574";
		if(requestType.isPresent() && query.isPresent() && !query.get().isEmpty()) {
		if(requestType.get().equalsIgnoreCase("UserID")) {
			 inp =String.valueOf(query.get());
		}else if(requestType.get().equalsIgnoreCase("MobileNo")) {
			 inp =String.valueOf(query.get());
			//inp="9912720574";
		}else if(requestType.get().equalsIgnoreCase("SMC")){
			 inp =String.valueOf(query.get());
		}
		
		MyplexUserUser user=new MyplexUserUser();
		
		user=subsService.findUserInformation(inp,requestType.get());
	//	log.info("user==========="+user);
		if(user==null) {
			model.addAttribute("message","Please Enter Valid Input");
			
		}
		//log.info("UserName: {}",user.getFirst());
		//log.info("SMC: {}",user.getSmc());
		//log.info("Mobile No: {}",user.getMobileNo());
		
		
		model.addAttribute("query",String.valueOf(query.get()));
		model.addAttribute("filter",String.valueOf(requestType.get()));
		model.addAttribute("user",user);
		}else {
			model.addAttribute("message","");
			
		}
		return "subscriber";
		
	}

}
