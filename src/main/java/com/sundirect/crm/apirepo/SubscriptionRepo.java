package com.sundirect.crm.apirepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sundirect.crm.smsentity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, String> {

}
