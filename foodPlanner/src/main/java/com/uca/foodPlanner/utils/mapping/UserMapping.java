package com.uca.foodPlanner.utils.mapping;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uca.foodPlanner.dto.UserDto;
import com.uca.foodPlanner.model.User;

@Component
public class UserMapping {

	@Autowired
	private DozerBeanMapper mapper;
	
	public User map(UserDto userDto) {
		return mapper.map(userDto, User.class);
	}
	
	public UserDto map(User user) {
		return mapper.map(user, UserDto.class);
	}
}
