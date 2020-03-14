package com.uca.foodPlanner.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uca.foodPlanner.dto.UserDto;
import com.uca.foodPlanner.exception.InvalidDataException;
import com.uca.foodPlanner.exception.NotFoundException;
import com.uca.foodPlanner.service.user.UserService;


@CrossOrigin(origins = { "http://localhost:8100", "http://localhost:8080" })
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDto> getAll() throws NotFoundException{
		return userService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDto getById(@PathVariable(value = "id") String id) throws NotFoundException {
		return userService.findById(id);
	}
	
//	@RequestMapping(value = "/email={email}", method = RequestMethod.GET)
//	public UserDto getByEmail(@PathVariable(value = "email") String email) throws NotFoundException {
//		return userService.findByEmail(email);
//	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes= "application/json")
	public UserDto create(@RequestBody @Valid UserDto userDto) throws InvalidDataException {
		return userService.create(userDto);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody UserDto userDto) {
		userService.update(userDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") String id) {
		userService.delete(id);
	}
}
