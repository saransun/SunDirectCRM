package com.sundirect.crm.service;

import java.util.List;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.MyplexUserUser;
import com.sundirect.crm.smsentity.Subscription;

public interface SubscriberService {

	MyplexUserUser findUserInformation(String id,String request);
	
	List<MyplexUserDevice> findDeviceInfoByUserId(Integer userId);	
	
	List<Subscription> findSubscriptionByuserId(Integer userId);
	
	List<Subscription> findExpiredSubscriptionByuserId(Integer userId);
	
}
