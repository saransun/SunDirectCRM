package com.sundirect.crm.service;

import java.util.List;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;

public interface SubscriberService {

	MyplexUserUser findUserInformation(String id,String request);
	
	List<MyplexUserDevice> findDeviceInfoByUserId(Integer userId);	
	
}
