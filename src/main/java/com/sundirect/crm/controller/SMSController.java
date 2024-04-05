package com.sundirect.crm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.bean.DeviceInfo;
import com.sundirect.crm.bean.Login;
import com.sundirect.crm.bean.MapObject;
import com.sundirect.crm.bean.MyplexUserUser;
import com.sundirect.crm.bean.UserInfo;
import com.sundirect.crm.config.AppUserService;
import com.sundirect.crm.service.APIService;
import com.sundirect.crm.service.SubscriberService;


@Controller
public class SMSController {
	
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	 
	@Autowired 
	AppUserService appUserService;
	 
	@Autowired
	APIService apiService;
	 
	@Value("${application.version}") 
	private String version;
	 
	@GetMapping(value = "/sms/subscriber/info")
	public String customerInfo(Model model, @RequestParam(value = "query", required = true) Optional<String> query,
			@RequestParam(value = "requestType") Optional<String> requestType) {
		Integer userId = 0;
		String param="";
		if (query.isPresent() && requestType.isPresent() && !query.get().isEmpty()) {
			log.info("Entering into impl=====22222");
			String inp = "";
			if (requestType.get().equalsIgnoreCase("UserID")) {
				inp = String.valueOf(query.get());
				param="user_id";
				userId=0;
			} else if (requestType.get().equalsIgnoreCase("MobileNo")) {
				inp = String.valueOf(query.get());
				param="rmn";
				userId=0;
			} else if (requestType.get().equalsIgnoreCase("SMC")) {
				inp = String.valueOf(query.get());
				param="smc";
				userId=0;
			}
			try {
				String resp=apiService.getUserInfo(query.get(), userId,param);
				JSONObject jsonObj=new JSONObject(resp);
				
				String responseFinal=jsonObj.getString("user_info");
				List<UserInfo> userInfoList=new ArrayList<UserInfo>();
				try {
					log.info("test: {}",resp.trim());
					
		            ObjectMapper mapper = new ObjectMapper();
		            userInfoList = mapper.readValue(responseFinal, new TypeReference<List<UserInfo>>(){});		            
		            log.info("checking userInfo.....{}",userInfoList.get(0).getFields().getFirst());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				model.addAttribute("user", userInfoList);
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
			String resp=apiService.getUserInfo(userId+"", 1,"user_id");
			JSONObject jsonObj=new JSONObject(resp);			
			String responseFinal=jsonObj.getString("device_info");
			List<DeviceInfo> deviceInfoList=new ArrayList<DeviceInfo>();
			try {
				log.info("test: {}",resp.trim());
				
	            ObjectMapper mapper = new ObjectMapper();
	            deviceInfoList = mapper.readValue(responseFinal, new TypeReference<List<DeviceInfo>>(){});		            
	            log.info("checking userInfo.....{}",deviceInfoList.get(0).getFields().getMake());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			log.info("Entering in Device Details........");
			//List<MyplexUserDevice> deviceList = new ArrayList<MyplexUserDevice>();
			List<MapObject> mapList = new ArrayList<MapObject>();
			//deviceList = subsService.findDeviceInfoByUserId(userId);
			model.addAttribute("deviceList", deviceInfoList);
			
			if (deviceInfoList.isEmpty()) {
				MapObject mapObject = new MapObject();
				mapObject.setMessage1("No Data available");
				mapList.add(mapObject);
				modelmap.put("deviceList", mapList);
				return modelmap;
			}
			model.addAttribute("appLaunch", deviceInfoList.size());
			int inc = 0;
			for (DeviceInfo my : deviceInfoList) {
				inc++;
				MapObject mapObject = new MapObject();
				mapObject.setInc(deviceInfoList.size());
				mapObject.setDevice(my);
				mapObject.setMessage("success");
				log.info("{} -  Device", inc);
				log.info("Os: {}", my.getFields().getOs());
				log.info("Os version: {}", my.getFields().getOs_version());
				log.info("Device make: {}", my.getFields().getMake());
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
}