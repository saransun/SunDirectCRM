package com.sundirect.crm.smsentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Asset {
@Id
private Integer id;
private String language;
private String name;
@Column(name = "studio_name", nullable = false)
private String studioName;
private String type;
@Column(name = "tenant_id", nullable = false)
private String tenantId;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStudioName() {
	return studioName;
}
public void setStudioName(String studioName) {
	this.studioName = studioName;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getTenantId() {
	return tenantId;
}
public void setTenantId(String tenantId) {
	this.tenantId = tenantId;
}


}
