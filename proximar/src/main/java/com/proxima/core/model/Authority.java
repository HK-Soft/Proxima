package com.proxima.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true, nullable = false)
	private String authority;

	@Column(nullable = false)
	private String description;

	public Authority() {
	}

	public Authority(long id, String authority, String description) {
		this.id = id;
		this.authority = authority;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
