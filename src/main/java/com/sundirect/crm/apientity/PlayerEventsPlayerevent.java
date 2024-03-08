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
	
}
