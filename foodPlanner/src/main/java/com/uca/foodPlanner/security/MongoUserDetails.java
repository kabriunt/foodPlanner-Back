package com.uca.foodPlanner.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.uca.foodPlanner.dto.UserDto;



public class MongoUserDetails implements UserDetails {

	private static final long serialVersionUID = -8703113019023065040L;
	
	private UserDto userDto;
	
    public MongoUserDetails(UserDto userDto) {
        this.userDto = userDto;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] authorities = {userDto.getRole()};
		return AuthorityUtils.createAuthorityList(authorities);
	}

	@Override
	public String getPassword() {
		return userDto.getPassword();
	}

	@Override
	public String getUsername() {
		return userDto.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return userDto.isNonExpiredAccount();
	}

	@Override
	public boolean isAccountNonLocked() {
		return userDto.isNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return userDto.isNonExpiredCredentials();
	}

	@Override
	public boolean isEnabled() {
		return userDto.isEnabled();
	}
}