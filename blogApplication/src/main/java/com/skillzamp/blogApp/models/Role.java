package com.skillzamp.blogApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;

	
	
	private String roleName;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getRole() {
		return roleName;
	}
	public void setRole(String role) {
		this.roleName = role;
	}
	public Role() {
		super();
	
	}
	
	
}
