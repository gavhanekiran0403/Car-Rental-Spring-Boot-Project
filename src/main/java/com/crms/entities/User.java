package com.crms.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crms.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{

	@Id
	private String userId;
    private String fullName;
    private String phoneNo;
    private String email;
    private String password;
    private Role role;
    private boolean status;
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(
				new SimpleGrantedAuthority("ROLE_"+ role.name())
				);
	}
    
	@Override
	public String getUsername() {
		
		return email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
