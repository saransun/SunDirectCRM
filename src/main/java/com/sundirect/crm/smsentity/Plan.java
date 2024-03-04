package com.sundirect.crm.smsentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plan")
public class Plan {
	@Id
	private Integer id;
	@Column(name = "button_text")
	private String buttonText;
	private String description;
	@Column(name = "has_eula")
	private boolean hasEula;
	@Column(name = "inserted_on", nullable = false)
	private Date insertedOn;
	private String name;
	private Integer precedence;
	@Column(name = "updated_on", nullable = false)
	private Date updatedOn;
	private Integer category;
	@Column(name = "plan_restrict", nullable = false)
	private Integer planRestrict;
	@Column(name = "status_id", nullable = false)
	private Integer statusId;
	private boolean ads;
	@Column(name = "devices_allowed", nullable = false)
	private Integer devicesAllowed;
	@Column(name = "is_platform_all", nullable = false)
	private boolean isPlatformAll;
	@Column(name = "un_subscription", nullable = false)
	private boolean unSubscription;
	@Column(name = "consumption_type", nullable = false)
	private Integer consumptionType;
	@Column(name = "country_id", nullable = false)
	private Integer countryId;
	@Column(name = "subscription_type", nullable = false)
	private Integer subscriptionType;
	private Integer tag;
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	@Column(name = "accessLabel", nullable = false)
	private Integer access_label;
	@Column(name = "concurrent_stream", nullable = false)
	private Integer concurrentStream;
	@Column(name = "chrome_cast", nullable = false)
	private boolean chromeCast;
	private boolean subtitle;
	private String quality;
	@Column(name = "max_playback_duration", nullable = false)
	private Integer maxPlaybackDuration;
	@Column(name = "payment_button_label", nullable = false)
	private String paymentButtonLabel;
	private Integer noofminutes;
	private Integer noofviews;
	private Date planstartdate;
	@Column(name = "post_booking_msg", nullable = false)
	private String postBookingMsg;
	@Column(name = "pre_booking_msg", nullable = false)
	private String preBookingMsg;
	@Column(name = "seek_enabled", nullable = false)
	private boolean seekEnabled;
	private Date subsstartdate;
	private Integer updatestatusintervalinsec;
	@Column(name = "countries_allowed", nullable = false)
	private String countriesAllowed;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isHasEula() {
		return hasEula;
	}
	public void setHasEula(boolean hasEula) {
		this.hasEula = hasEula;
	}
	public Date getInsertedOn() {
		return insertedOn;
	}
	public void setInsertedOn(Date insertedOn) {
		this.insertedOn = insertedOn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrecedence() {
		return precedence;
	}
	public void setPrecedence(Integer precedence) {
		this.precedence = precedence;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getPlanRestrict() {
		return planRestrict;
	}
	public void setPlanRestrict(Integer planRestrict) {
		this.planRestrict = planRestrict;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public boolean isAds() {
		return ads;
	}
	public void setAds(boolean ads) {
		this.ads = ads;
	}
	public Integer getDevicesAllowed() {
		return devicesAllowed;
	}
	public void setDevicesAllowed(Integer devicesAllowed) {
		this.devicesAllowed = devicesAllowed;
	}
	public boolean isPlatformAll() {
		return isPlatformAll;
	}
	public void setPlatformAll(boolean isPlatformAll) {
		this.isPlatformAll = isPlatformAll;
	}
	public boolean isUnSubscription() {
		return unSubscription;
	}
	public void setUnSubscription(boolean unSubscription) {
		this.unSubscription = unSubscription;
	}
	public Integer getConsumptionType() {
		return consumptionType;
	}
	public void setConsumptionType(Integer consumptionType) {
		this.consumptionType = consumptionType;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(Integer subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Integer getAccess_label() {
		return access_label;
	}
	public void setAccess_label(Integer access_label) {
		this.access_label = access_label;
	}
	public Integer getConcurrentStream() {
		return concurrentStream;
	}
	public void setConcurrentStream(Integer concurrentStream) {
		this.concurrentStream = concurrentStream;
	}
	public boolean isChromeCast() {
		return chromeCast;
	}
	public void setChromeCast(boolean chromeCast) {
		this.chromeCast = chromeCast;
	}
	public boolean isSubtitle() {
		return subtitle;
	}
	public void setSubtitle(boolean subtitle) {
		this.subtitle = subtitle;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public Integer getMaxPlaybackDuration() {
		return maxPlaybackDuration;
	}
	public void setMaxPlaybackDuration(Integer maxPlaybackDuration) {
		this.maxPlaybackDuration = maxPlaybackDuration;
	}
	public String getPaymentButtonLabel() {
		return paymentButtonLabel;
	}
	public void setPaymentButtonLabel(String paymentButtonLabel) {
		this.paymentButtonLabel = paymentButtonLabel;
	}
	public Integer getNoofminutes() {
		return noofminutes;
	}
	public void setNoofminutes(Integer noofminutes) {
		this.noofminutes = noofminutes;
	}
	public Integer getNoofviews() {
		return noofviews;
	}
	public void setNoofviews(Integer noofviews) {
		this.noofviews = noofviews;
	}
	public Date getPlanstartdate() {
		return planstartdate;
	}
	public void setPlanstartdate(Date planstartdate) {
		this.planstartdate = planstartdate;
	}
	public String getPostBookingMsg() {
		return postBookingMsg;
	}
	public void setPostBookingMsg(String postBookingMsg) {
		this.postBookingMsg = postBookingMsg;
	}
	public String getPreBookingMsg() {
		return preBookingMsg;
	}
	public void setPreBookingMsg(String preBookingMsg) {
		this.preBookingMsg = preBookingMsg;
	}
	public boolean isSeekEnabled() {
		return seekEnabled;
	}
	public void setSeekEnabled(boolean seekEnabled) {
		this.seekEnabled = seekEnabled;
	}
	public Date getSubsstartdate() {
		return subsstartdate;
	}
	public void setSubsstartdate(Date subsstartdate) {
		this.subsstartdate = subsstartdate;
	}
	public Integer getUpdatestatusintervalinsec() {
		return updatestatusintervalinsec;
	}
	public void setUpdatestatusintervalinsec(Integer updatestatusintervalinsec) {
		this.updatestatusintervalinsec = updatestatusintervalinsec;
	}
	public String getCountriesAllowed() {
		return countriesAllowed;
	}
	public void setCountriesAllowed(String countriesAllowed) {
		this.countriesAllowed = countriesAllowed;
	}
	
	

}

