package com.sundirect.crm.apirepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.apientity.MyplexUserUser;

public interface SubscriberRepo extends JpaRepository<MyplexUserUser, Integer>{
	
	Optional<MyplexUserUser> findById(Integer id);
	
	//Optional<MyplexUserUser> findByMobileNo(Long mobileNo);
	Optional<List<MyplexUserUser>> findByMobileNo(Long mobileNo);
	Optional<List<MyplexUserUser>> findBySmc(String smc);
	
	
	
}
