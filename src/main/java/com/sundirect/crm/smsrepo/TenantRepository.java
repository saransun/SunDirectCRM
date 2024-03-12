package com.sundirect.crm.smsrepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.smsentity.Tenant;




public interface TenantRepository extends JpaRepository<Tenant, Integer>{

	
	Optional<Tenant> findByTenantId(String tenantId);
	Optional<Tenant> findByTenantName(String tenantName);
	
}
