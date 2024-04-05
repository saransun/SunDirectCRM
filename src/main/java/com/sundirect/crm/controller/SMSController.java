package com.sundirect.crm.controller;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.apientity.PlayerEventsPlayerevent;
import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.bean.MapObject;
import com.sundirect.crm.config.AppUserService;
import com.sundirect.crm.service.APIService;
import com.sundirect.crm.service.SubscriberService;
import com.sundirect.crm.smsentity.Asset;
import com.sundirect.crm.smsentity.Subscription;
 
@Controller
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	@Autowired
	SubscriberService subsService;
	
	@Autowired
	AppUserService appUserService;
	
	
	@Value("${application.version}")
	private String version;
	@Autowired
	APIService apiservice;
 
	@GetMapping(value = "/sms/subscriber/info")
	public String customerInfo(Model model, @RequestParam(value = "query", required = true) Optional<String> query,
			@RequestParam(value = "requestType") Optional<String> requestType) {
		Integer userId = 0;
		if (query.isPresent() && requestType.isPresent() && !query.get().isEmpty()) {
			log.info("Entering into impl=====22222");
			String inp = "";
			if (requestType.get().equalsIgnoreCase("UserID")) {
				inp = String.valueOf(query.get());
			} else if (requestType.get().equalsIgnoreCase("MobileNo")) {
				inp = String.valueOf(query.get());
			} else if (requestType.get().equalsIgnoreCase("SMC")) {
				inp = String.valueOf(query.get());
			}
			try {
				// MyplexUserUser user=new MyplexUserUser();
				List<MyplexUserUser> userList = new ArrayList<MyplexUserUser>();
				userList = subsService.findUserList(inp, requestType.get());
				// model.addAttribute("userList",userList);
				// userId=user.getId();
				/*
				 * log.info("UserName: {}",user.getFirst()); log.info("SMC: {}",user.getSmc());
				 * log.info("Mobile No: {}",user.getMobileNo());
				 */
				model.addAttribute("user", userList);
				model.addAttribute("query", String.valueOf(query.get()));
				model.addAttribute("filter", requestType.get());
			} catch (NoSuchElementException e) {
				log.info("User not found: {}", e.getMessage());
				model.addAttribute("message", "User not found");
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Exception occured: {}", e.getMessage());
				e.printStackTrace();
				model.addAttribute("message", "Please Enter Valid Input");
			}
		} else {
			//model.addAttribute("query", String.valueOf(query.get()));
			//model.addAttribute("filter", requestType.get());
			model.addAttribute("message", "");
		}
		return "subscriber";
	}
 
	@GetMapping(value = "/deviceInfo")
	public @ResponseBody Map<String, List<MapObject>> getCustomerDevice(Model model,
			@RequestParam(value = "userId", required = true) Integer userId) {
		Map<String, List<MapObject>> modelmap = new HashMap<String, List<MapObject>>();
		try {
			log.info("Entering in Device Details........");
			List<MyplexUserDevice> deviceList = new ArrayList<MyplexUserDevice>();
			List<MapObject> mapList = new ArrayList<MapObject>();
			deviceList = subsService.findDeviceInfoByUserId(userId);
			model.addAttribute("deviceList", deviceList);
			
			if (deviceList.isEmpty()) {
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Data available");
				mapList.add(mapObject);
				modelmap.put("deviceList", mapList);
				return modelmap;
			}
			model.addAttribute("appLaunch", deviceList.size());
			int inc = 0;
			for (MyplexUserDevice my : deviceList) {
				inc++;
				MapObject mapObject = new MapObject();
				mapObject.setInc(deviceList.size());
				mapObject.setDevice(my);
				mapObject.setMessage("success");
				log.info("{} -  Device", inc);
				log.info("Os: {}", my.getOs());
				log.info("Os version: {}", my.getOs_version());
				log.info("Device make: {}", my.getMake());
				log.info("Model: {}", my.getModel());
				mapList.add(mapObject);
			}
			// MapObject mapObject=new MapObject();
			// mapList.add(mapObject);
			modelmap.put("deviceList", mapList);
 
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured in device info: ", e.getMessage());
			model.addAttribute("message", "No Data available");
			MapObject mapObject = new MapObject();
			mapObject.setMessage("No Data available");
			List<MapObject> mapList = new ArrayList<MapObject>();
			mapList.add(mapObject);
			modelmap.put("message", mapList);
		}
		return modelmap;
	}
 
	@GetMapping(value = "/subscriptionInfo")
	public @ResponseBody Map<String, List<MapObject>> getCustomerSubscription(Model model,
			@RequestParam(value = "userId", required = true) Integer userId) {
		Map<String, List<MapObject>> modelmap = new HashMap<String, List<MapObject>>();
		try {
 
			List<MapObject> mapList = new ArrayList<MapObject>();
			log.info("Entering in Subscription Details........");
			List<Subscription> activeSubscriptionList = new ArrayList<Subscription>();
			List<Subscription> expiredSubscriptionList = new ArrayList<Subscription>();
			activeSubscriptionList = subsService.findSubscriptionByuserId(userId);
			if (activeSubscriptionList.isEmpty()) {
				log.info("No Active subscription........");
				model.addAttribute("message1", "No Active subscription available");
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Active subscription available");
				mapList.add(mapObject);
				modelmap.put("message1", mapList);
			}
			for (Subscription s : activeSubscriptionList) {
				MapObject mapObject1 = new MapObject();
				mapObject1.setSub(s);
				mapObject1.setMessage("success");
				mapList.add(mapObject1);
				log.info("Active plan information: {}", s.getPlan().getName());
			}
			modelmap.put("Active", mapList);
			model.addAttribute("Active", activeSubscriptionList);
			List<MapObject> mapList1 = new ArrayList<MapObject>();
			expiredSubscriptionList = subsService.findExpiredSubscriptionByuserId(userId);
			if (expiredSubscriptionList.isEmpty()) {
				log.info("No expired subscription........");
				model.addAttribute("message1", "No Expired subscription available");
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Expired subscription available");
				mapList1.add(mapObject);
				modelmap.put("message1", mapList1);
			}
			for (Subscription s : expiredSubscriptionList) {
				MapObject mapObject1 = new MapObject();
				mapObject1.setSub(s);
				mapObject1.setMessage("success");
				mapList1.add(mapObject1);
				log.info("Expired plan information: {}", s.getPlan().getName());
			}
			modelmap.put("Expired", mapList1);
			model.addAttribute("Expired", expiredSubscriptionList);
		} catch (Exception e) {
			log.info("Exception occured in device info: ", e.getMessage());
			model.addAttribute("message", "No subscription available");
			MapObject mapObject = new MapObject();
			mapObject.setMessage("No subscription available");
			List<MapObject> mapList = new ArrayList<MapObject>();
			mapList.add(mapObject);
			modelmap.put("message", mapList);
		}
		return modelmap;
	}
 
	@GetMapping(value = "/otherInfo")
	public @ResponseBody Map<String, List<MapObject>> getCustomerOtherInfo(Model model,
			@RequestParam(value = "userId", required = true) Integer userId) {
		Map<String, List<MapObject>> modelmap = new HashMap<String, List<MapObject>>();
		try {
			List<PlayerEventsPlayerevent> eventList = subsService.findPlayerevents(userId);
			List<MapObject> mapList = new ArrayList<MapObject>();
			model.addAttribute("", eventList);
			if (eventList.isEmpty()) {
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Data available");
				mapList.add(mapObject);
				modelmap.put("otherInfo", mapList);
				return modelmap;
			}
			for (PlayerEventsPlayerevent p : eventList) {
				MapObject mapObject = new MapObject();
				log.info("Last 7 days Event List");
				Asset asset = subsService.findByAsset(Integer.parseInt(p.getContentId()));
				mapObject.setAsset(asset);
				mapObject.setMessage("success");
				log.info("Action: {}", p.getAction());
				mapObject.setPlay(p);
				mapList.add(mapObject);
			}
			modelmap.put("otherInfo", mapList);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Exception occured in device info: ", e.getMessage());
			model.addAttribute("message", "No Player Event available");
			MapObject mapObject = new MapObject();
			mapObject.setMessage("No Player Event available");
			List<MapObject> mapList = new ArrayList<MapObject>();
			mapList.add(mapObject);
			modelmap.put("message", mapList);
		}
 
		return modelmap;
	}
	
	@GetMapping("/login/form")
	public String login(Model model) {
		log.info("model from /login/form" +model);
		AppUser user = appUserService.getCurrentUser();
		if (null == user) {
			log.info("user" +user);
			model.addAttribute("version", version);
			return "login";
		}
		return "redirect:/";
		
	}
	@GetMapping("/sms/plans/info")
	public String allPlans(Model model,@RequestParam(name = "status") String status) {
		String returnVal=apiservice.getAllPlanAPI(status);
		model.addAttribute("planValue", returnVal);
		return "plan1";
		
	}
	@GetMapping(value = "/api/Allplans")
	public String getAllPlan(Model model,@RequestParam(name = "status") String status,HttpServletRequest request) {			
		
		try {
			String returnVal=apiservice.getAllPlanAPI(status);
		//	JSONObject jsonObj=new JSONObject(returnVal);			
			ObjectMapper objectMapper=new ObjectMapper();        
			JsonNode jsonNode= objectMapper.readTree(returnVal);
			log.info("json string: {}",jsonNode.get("results").get(0).get("planId"));
			
			model.addAttribute("planValue", jsonNode);
			return "plan";
			
		}catch (Exception e) {
			e.printStackTrace();
			log.info("Exception occur: {}",e.getMessage());
			return null;
		}
	
	}
}