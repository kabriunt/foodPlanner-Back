package com.uca.foodPlanner.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
	
	private static final long serialVersionUID = -7433188215556539347L;

	private String id;
	private String username;
	private String email;
	private String password;
	private String confirm;
	private String role;
	
	private boolean nonExpiredAccount;
	private boolean nonLocked;
	private boolean nonExpiredCredentials;
	private boolean enabled;
	
}
