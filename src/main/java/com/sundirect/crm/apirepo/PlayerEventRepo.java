package com.sundirect.crm.apirepo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.apientity.PlayerEventsPlayerevent;

public interface PlayerEventRepo extends JpaRepository<PlayerEventsPlayerevent, Long> {	
	List<PlayerEventsPlayerevent>  findByUserIdAndCreatedAtGreaterThan(Integer userId, Date sevenDayBefore);
}
