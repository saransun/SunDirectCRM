package com.sundirect.crm.smsentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table
public class Tenant {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="tenant_id", nullable=false)
	private String tenantId;
	
	@Column(name="tenant_name", nullable=false)
	private String tenantName;
	
	@Column(name="status", nullable=false)
	private String status;

	//@OneToOne(fetch=FetchType.EAGER, mappedBy="tenant")
		//private Asset asset;
    @Temporal(TemporalType.TIMESTAMP) 
	private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(name="primary_tenant_name", nullable=false)
    private String primaryTenantName;

	public Tenant(String tenantId, String tenantName, String status,String primaryTenantName) {
		super();
		
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.status = status;
		this.primaryTenantName=primaryTenantName;
	}

	public Tenant() {		
	}

	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPrimaryTenantName() {
		return primaryTenantName;
	}

	public void setPrimaryTenantName(String primaryTenantName) {
		this.primaryTenantName = primaryTenantName;
	}

	@Override
	public String toString() {
		return "Tenant [Id=" + Id + ", tenantId=" + tenantId + ", tenantName=" + tenantName + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", primaryTenantName=" + primaryTenantName
				+ "]";
	}

}