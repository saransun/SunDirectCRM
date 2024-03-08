package com.sundirect.crm.apientity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="player_events_playerevent")
public class PlayerEventsPlayerevent {
	@Id
	private Long id;
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	@Column(name = "content_id", nullable = false)
	private String contentId;
	@Column(name = "device_id", nullable = false)
	private String deviceId;
	private String action;
	@Column(name = "elapsed_time", nullable = false)
	private Long elapsedTime;
	@Column(name = "total_duration", nullable = false)
	private Long totalDuration;
	@Column(name = "stream_name", nullable = false)
	private String streamName;
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	@Column(name = "modified_at", nullable = false)
	private Date modifiedAt;
	@Column(name = "video_startup_time", nullable = false)
	private String videoStartupTime;
	@Column(name = "buffering_ratio", nullable = false)
	private String bufferingRatio;
	@Column(name = "average_bitrate", nullable = false)
	private String averageBitrate;
	@Column(name = "connection_speed", nullable = false)
	private String connectionSpeed;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public Long getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getVideoStartupTime() {
		return videoStartupTime;
	}
	public void setVideoStartupTime(String videoStartupTime) {
		this.videoStartupTime = videoStartupTime;
	}
	public String getBufferingRatio() {
		return bufferingRatio;
	}
	public void setBufferingRatio(String bufferingRatio) {
		this.bufferingRatio = bufferingRatio;
	}
	public String getAverageBitrate() {
		return averageBitrate;
	}
	public void setAverageBitrate(String averageBitrate) {
		this.averageBitrate = averageBitrate;
	}
	public String getConnectionSpeed() {
		return connectionSpeed;
	}
	public void setConnectionSpeed(String connectionSpeed) {
		this.connectionSpeed = connectionSpeed;
	}
	
	
}
