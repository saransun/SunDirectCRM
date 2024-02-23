package com.sundirect.crm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.apirepo.SubscriberRepo;
@Component
@Transactional
public class SubscriberServiceImpl implements SubscriberService{
	
	@Autowired
	SubscriberRepo subsInfo;
	
	@Override
	public MyplexUserUser findUserInformation(String id,String request) {
		// TODO Auto-generated method stub
		//Integer ids=Integer.parseInt(id);
		if(request.equalsIgnoreCase("UserID")) {
			Optional<MyplexUserUser> userinfo=subsInfo.findById(Integer.parseInt(id));
			return userinfo.get();
		}else if(request.equalsIgnoreCase("MobileNo")) {
			Optional<MyplexUserUser> userinfo=subsInfo.findByMobileNo(Long.parseLong(id));
			return userinfo.get();
		}else if(request.equalsIgnoreCase("SMC")){
			Optional<MyplexUserUser> userinfo=subsInfo.findBySmc(id);
			return userinfo.get();
		}
		//Optional<MyplexUserUser> userinfo=subsInfo.findById(ids);		
		return null;
	}

	
}
