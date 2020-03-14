package com.uca.foodPlanner.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uca.foodPlanner.dao.UserDao;
import com.uca.foodPlanner.dto.ErrorDto;
import com.uca.foodPlanner.dto.UserDto;
import com.uca.foodPlanner.exception.InvalidDataException;
import com.uca.foodPlanner.exception.NotFoundException;
import com.uca.foodPlanner.model.User;
import com.uca.foodPlanner.utils.mapping.UserMapping;
import com.uca.foodPlanner.utils.validate.UserValidate;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bcryp;
	
	@Autowired
	private PasswordEncoder bCryp;
	
	@Autowired
	private UserMapping mapping;

	@Override
	public List<UserDto> findAll() throws NotFoundException {
		Iterable<User> users = userDao.findAll();
		if(users!=null) {
			final List<UserDto> userDtos = new ArrayList<>();
			users.forEach(x->userDtos.add(mapping.map(x)));
			return userDtos;
		}
		throw new NotFoundException();
	}

	@Override
	public UserDto findById(String oId) throws NotFoundException{
		final Optional<User> user = userDao.findById(oId);
		if(user.isPresent())
			return mapping.map(user.get());
		throw new NotFoundException();
	}
	
	@Override
	public UserDto findByUsername(String username) throws NotFoundException{
		final User user= userDao.findByUsername(username);
		if (user!=null)
			return mapping.map(user);
		throw new NotFoundException();
	}
	
	@Override
	public UserDto create(UserDto userDto) throws InvalidDataException {
		List<ErrorDto> errorList = new ArrayList<>();
		errorList = UserValidate.validate(userDto);
		boolean pruebaPassbCryp = bCryp.matches(bCryp.encode(userDto.getPassword()), bCryp.encode(userDto.getConfirm()));
		boolean pruebaPassbCryp2 = bCryp.matches(bCryp.encode(userDto.getPassword()), bCryp.encode(userDto.getConfirm()));
		boolean pruebaPassbcryp = bcryp.matches(bcryp.encode(userDto.getPassword()), bcryp.encode(userDto.getConfirm()));
		boolean pruebaPassbcryp2 = bcryp.matches(bcryp.encode(userDto.getPassword()), bcryp.encode(userDto.getConfirm()));
		
		userDto.setPassword(bCryp.encode(userDto.getPassword()));
		userDto.setConfirm(bCryp.encode(userDto.getConfirm()));
		userDto.setEnabled(true);
		userDto.setNonExpiredAccount(true);
		userDto.setNonExpiredCredentials(true);
		userDto.setNonLocked(true);
		userDto.setRole("USER");
		
		if(errorList.isEmpty())
			return mapping.map(userDao.save(mapping.map(userDto)));
		throw new InvalidDataException("Error, faltan datos");
	}
	
	@Override
	public void update(UserDto userDto) {
		userDao.save(mapping.map(userDto));
	}

	@Override
	public void delete(String oId) {
		userDao.deleteById(oId);		
	}

}
