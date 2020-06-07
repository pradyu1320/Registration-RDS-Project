package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.experimental.SuperBuilder;
@SuperBuilder
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	@Column(name="user_name")
	@Length(min=5,message="*Your user name must have at least 5 characters")
	@NotEmpty(message = "* Please provide a user name")
	private String userName;
	@Column(name="email")
	@Email(message="* Please provide a valid email")
	@NotEmpty(message ="* Please provide an email")
	private String email;	
	@Column(name="password")
	@Length(min=5,message="* Your password must have at least 5 characters")
	@NotEmpty(message="* Please provide a password")
	private String password;
	@Column(name="name")
	@NotEmpty(message="* Please provide a your name")
	private String name;
	@Column(name="last_name")
	@NotEmpty(message="* Please provide your last name")
	private String lastName;
	@Column(name="active")
	private Boolean active;
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public User(
			@Length(min = 5, message = "*Your user name must have at least 5 characters") @NotEmpty(message = "* Please provide a user name") String userName,
			@Email(message = "* Please provide a valid email") @NotEmpty(message = "* Please provide an email") String email,
			@Length(min = 5, message = "* Your password must have at least 5 characters") @NotEmpty(message = "* Please provide a password") String password,
			@NotEmpty(message = "* Please provide a your name") String name,
			@NotEmpty(message = "* Please provide your last name") String lastName, Boolean active, Set<Role> roles) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active = active;
		this.roles = roles;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", lastName=" + lastName + ", active=" + active + ", roles=" + roles + "]";
	}
	
	
}

