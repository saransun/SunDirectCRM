package com.sundirect.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.apirepo.DeviceRepo;
import com.sundirect.crm.apirepo.SubscriberRepo;
@Component
@Transactional
public class SubscriberServiceImpl implements SubscriberService{
	
	@Autowired
	SubscriberRepo subsInfo;
	
	@Autowired
	DeviceRepo deviceInfo;
	
	@Override
	public MyplexUserUser findUserInformation(String id,String request) {
		// TODO Auto-generated method stub
		//Integer ids=Integer.parseInt(id);
		try {
		if(request.equalsIgnoreCase("UserID")) {
			System.out.print("userinfo-----------");
			Optional<MyplexUserUser> userinfo=subsInfo.findById(Integer.parseInt(id));
			System.out.print("userinfo-----------"+userinfo);
			return userinfo.get();
		}else if(request.equalsIgnoreCase("MobileNo")) {
			System.out.print("MobileNo-----------");
			Optional<MyplexUserUser> userinfo=subsInfo.findByMobileNo(Long.parseLong(id));
			System.out.print("MobileNo-----------"+userinfo);
			return userinfo.get();
		}else if(request.equalsIgnoreCase("SMC")){
			Optional<MyplexUserUser> userinfo=subsInfo.findBySmc(id);
			return userinfo.get();
		}
		}catch (Exception e) {
			System.out.print(e.toString());
		}
		//Optional<MyplexUserUser> userinfo=subsInfo.findById(ids);		
		return null;
	}

	@Override
	public List<MyplexUserDevice> findDeviceInfoByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<MyplexUserDevice> deviceList=new ArrayList<MyplexUserDevice>();
		
		deviceList=deviceInfo.findByUserId(userId);
		
		return deviceList;
	}

	
}
