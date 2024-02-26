package com.sundirect.crm.apirepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.apientity.MyplexUserDevice;

public interface DeviceRepo extends JpaRepository<MyplexUserDevice, Integer>{
	
	List<MyplexUserDevice> findByUserId(Integer userId);

}
