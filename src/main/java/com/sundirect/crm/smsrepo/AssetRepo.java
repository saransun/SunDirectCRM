package com.sundirect.crm.smsrepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.smsentity.Asset;

public interface AssetRepo extends JpaRepository<Asset, Integer>{
	
	Optional<Asset> findById(Integer id);

}
