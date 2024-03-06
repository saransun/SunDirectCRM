package com.sundirect.crm.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.sundirect.crm.service.SubscriberService;

@Controller
//@RequestMapping(value = "/sms/")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@Autowired
	SubscriberService subsService;
	
	
	@GetMapping(value = "/sms/subscriber/info")
	public String customerInfo(Model model, @RequestParam(value = "query", required = true) Optional<String> query,
			@RequestParam(value = "requestType") Optional<String> requestType) {
		Integer userId = 0;
		if (query.isPresent() && requestType.isPresent() && !query.get().isEmpty()) {
			String inp = "";
			if (requestType.get().equalsIgnoreCase("UserID")) {
				inp = String.valueOf(query.get());
			} else if (requestType.get().equalsIgnoreCase("MobileNo")) {
				inp = String.valueOf(query.get());
			} else if (requestType.get().equalsIgnoreCase("SMC")) {
				inp = String.valueOf(query.get());
			}
			MyplexUserUser user = new MyplexUserUser();

			try {
				user = subsService.findUserInformation(inp, requestType.get());

				model.addAttribute(user);
				userId = user.getId();
				if (userId != 0) {
					try {
						List<MyplexUserDevice> deviceList = new ArrayList<MyplexUserDevice>();
						deviceList = subsService.findDeviceInfoByUserId(userId);

						model.addAttribute("deviceList", deviceList);

					} catch (Exception e) {
						// TODO: handle exception
						log.info("Exception occured in device info: ", e.getMessage());
						model.addAttribute("message", "No Data available");
					}
				} else {
					model.addAttribute("message", "No Data available");
				}
				log.info("UserName: {}", user.getFirst());
				log.info("SMC: {}", user.getSmc());
				log.info("Mobile No: {}", user.getMobileNo());
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Exception occured: ", e.getMessage());
				model.addAttribute("message", "Please Enter Valid Input");
			}
			model.addAttribute("user", user);
			model.addAttribute("filter", requestType.get());
			model.addAttribute("query", String.valueOf(query.get()));

		} else {
			model.addAttribute("message", "");

		}
		return "subscriber";
	}

	@GetMapping(value="device")
	public String getCustomerDevice(Model model, @RequestParam(value="userId",required = true) Integer userId) {
		
		List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
		deviceList=subsService.findDeviceInfoByUserId(userId);		
		model.addAttribute("Device", deviceList);		
		int inc=0;
		for(MyplexUserDevice my:deviceList) {
			inc++;
			log.info("{} -  Device",inc);
			log.info("Os: {}",my.getOs());
			log.info("Os version: {}",my.getOs_version());
			log.info("Device make: {}",my.getMake());
			log.info("Model: {}",my.getModel());
		}
		
		return "device";
	}

}