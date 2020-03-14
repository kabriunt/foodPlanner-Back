package com.uca.foodPlanner.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User implements Serializable{

	private static final long serialVersionUID = -5489710083344226805L;

	@NotEmpty
	private String username;
		
	@NotEmpty
	private String email;
		
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String role;
	
	@NotEmpty
	private boolean nonExpiredAccount;
	
	@NotEmpty
	private boolean nonLocked;
	
	@NotEmpty
	private boolean nonExpiredCredentials;
	
	@NotEmpty
	private boolean enabled;
	 
}
