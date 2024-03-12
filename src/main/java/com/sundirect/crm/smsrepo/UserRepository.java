package com.sundirect.crm.smsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sundirect.crm.smsentity.Login;



public interface UserRepository extends JpaRepository<Login, Integer>{

	Login findByUsername(String username);
	
	String findByTenantName(String tenantname);
}
