package com.uca.foodPlanner.service.user;

import java.util.List;

import com.uca.foodPlanner.dto.UserDto;
import com.uca.foodPlanner.exception.InvalidDataException;
import com.uca.foodPlanner.exception.NotFoundException;


public interface UserService {
	
	public List<UserDto> findAll() throws NotFoundException;
	
	public UserDto findById(String oId) throws NotFoundException;
	
	public UserDto findByUsername(String username) throws NotFoundException;
	
//	public UserDto findByEmail(String email);
	
	public UserDto create(UserDto userDto) throws InvalidDataException;
	
	public void update(UserDto userDto);
	
	public void delete(String oId);



	

	
}
