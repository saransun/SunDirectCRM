package com.sundirect.crm.smsentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {
    @Id
	private String uuid ;
	private Date cancelled_on;
	private Date inserted_on;
	private Date updated_on;
	@Column(name = "valid_from", nullable = false)
	private Date validFrom;
	@Column(name = "valid_till", nullable = false)
	private Date validTill;
	@Column(name = "order_id", nullable = false)
	private String orderId;
	@Column(name = "plan_id", nullable = false)
	private Integer planId;
	@Column(name = "status_id", nullable = false)
	private Integer statusId;
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	@Column(name = "autoRenew", nullable = false)
	private Boolean auto_renew;
	@Column(name = "tenantId", nullable = false)
	private String tenant_id;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getCancelled_on() {
		return cancelled_on;
	}
	public void setCancelled_on(Date cancelled_on) {
		this.cancelled_on = cancelled_on;
	}
	public Date getInserted_on() {
		return inserted_on;
	}
	public void setInserted_on(Date inserted_on) {
		this.inserted_on = inserted_on;
	}
	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTill() {
		return validTill;
	}
	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getAuto_renew() {
		return auto_renew;
	}
	public void setAuto_renew(Boolean auto_renew) {
		this.auto_renew = auto_renew;
	}
	public String getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	} 
	
	
	


	
	
}
