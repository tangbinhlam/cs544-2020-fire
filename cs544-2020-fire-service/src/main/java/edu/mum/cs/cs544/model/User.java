package edu.mum.cs.cs544.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	private String password;
	
	public User() {
		
	}
	
}
