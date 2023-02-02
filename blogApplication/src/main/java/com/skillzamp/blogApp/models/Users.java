package com.skillzamp.blogApp.models;



import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Users implements UserDetails {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private  int id;
		
		@Column(name="name" , nullable=false, length=20)
	
		private String name;
	
		private String email;
	
		private String password;
	
		private String about;
		
		@OneToMany(mappedBy="users" , cascade=CascadeType.ALL)
		private List<Posts> posts;
		
		@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		@JoinTable(name="user_role",
		joinColumns=@JoinColumn(name="users",referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="role",referencedColumnName="id")
		)
		private Set<Role> roles=new HashSet<>();
		
		public String getAbout() {
			return about;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Users() {
			super();
			
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<SimpleGrantedAuthority> authorities=  roles.stream().map((role)-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
			return authorities;
		}
		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}
		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
		
}
