package com.uca.foodPlanner.utils.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.uca.foodPlanner.dto.ErrorDto;
import com.uca.foodPlanner.dto.UserDto;

@Component
public class UserValidate {
	// Patrón para validar el email
	private static final Pattern PATTERN_EMAIL = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	public static List<ErrorDto> validate(UserDto userDto) {
		List<ErrorDto> errorList = new ArrayList<>();
		
		if (!validateNoEmpty(userDto)) {
			ErrorDto e = new ErrorDto(001, "Faltan Datos");
			errorList.add(e);
		}else if (!validateEmail(userDto.getEmail())) {
			ErrorDto e = new ErrorDto(002, "Email no válido.");
			errorList.add(e);
		}else if (!validatePassword(userDto)) {
			ErrorDto e = new ErrorDto(003,"Contraseña no válida, debe ser > 8 caracteres con MAY, MIN y NUM.");
			errorList.add(e);
		}else if (!validatePasswordEqualConfirm(userDto)) {
			ErrorDto e = new ErrorDto(003, "Contraseña no válida, revise que las contraseñas coinciden.");
			errorList.add(e);
		}
		
		return errorList;
	}

	private static boolean validateNoEmpty(UserDto userDto) {
		return (StringUtils.isNotBlank(userDto.getUsername()) && StringUtils.isNotBlank(userDto.getEmail())
				&& StringUtils.isNotBlank(userDto.getPassword()) && StringUtils.isNotBlank(userDto.getConfirm()));
	}

	private static boolean validateEmail(String email) {
		return PATTERN_EMAIL.matcher(email).find();
	}
	
	private static boolean validatePassword(UserDto userDto) {
		boolean hasNum = false;
		boolean hasCap = false;
		boolean hasLow = false;
		String password = userDto.getPassword();
		
		if (password.length()>=8) {
			for (char c : password.toCharArray()) {
				if (Character.isDigit(c)) {
					hasNum = true;
				}else if (Character.isUpperCase(c)) {
					hasCap = true;
				}else if (Character.isLowerCase(c)) {
					hasLow = true;
				}
			}
			if (hasNum && hasCap && hasLow) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean validatePasswordEqualConfirm(UserDto userDto) {
		return userDto.getPassword().equals(userDto.getConfirm());
	}
}
