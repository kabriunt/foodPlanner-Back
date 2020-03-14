package com.uca.foodPlanner.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto implements Serializable{

	private static final long serialVersionUID = -9037445302589022506L;
	
	private Integer code;
	
	private String message;
	
}