package com.sundirect.crm.smsrepo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.smsentity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, String>{
	
	List<Subscription> findByUserIdAndValidTillGreaterThan(Integer userId,Date validTill);
	
	List<Subscription> findByUserIdAndValidTillLessThan(Integer userId,Date validTill);
	
	List<Subscription> findByUserId(Integer userId);
}
