package com.sundirect.crm.smsentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Role {

	@Id
	@Column(name = "id", nullable = false, length = 5)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "authority", nullable = false, length = 32)
	private String authority;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uuid", nullable = false)
	private Login user;

	public Role(String authority, Login user) {
		super();
		this.authority = authority;
		this.user = user;
	}

	public Role() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Login getUser() {
		return user;
	}

	public void setUser(Login user) {
		this.user = user;
	}

}
