package com.sundirect.crm.bean;

import com.sundirect.crm.apientity.MyplexUserDevice;
import com.sundirect.crm.apientity.PlayerEventsPlayerevent;
import com.sundirect.crm.smsentity.Asset;
import com.sundirect.crm.smsentity.Subscription;

public class MapObject {
	
	private Subscription sub;
	private MyplexUserDevice device;
	private PlayerEventsPlayerevent play;
	private String message;
	private String message1;
	private Integer inc;
	
	public Integer getInc() {
		return inc;
	}
	public void setInc(Integer inc) {
		this.inc = inc;
	}
	public Subscription getSub() {
		return sub;
	}
	public void setSub(Subscription sub) {
		this.sub = sub;
	}
	public MyplexUserDevice getDevice() {
		return device;
	}
	public void setDevice(MyplexUserDevice device) {
		this.device = device;
	}
	public PlayerEventsPlayerevent getPlay() {
		return play;
	}
	public void setPlay(PlayerEventsPlayerevent play) {
		this.play = play;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	
	private Asset asset;
	 
	public Asset getAsset() {
		return asset;
	}
 
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	

}
