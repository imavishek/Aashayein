package org.avishek.aashayein.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.avishek.aashayein.entities.Employee;
import org.avishek.aashayein.entities.EmployeeModule;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Employee user;

	public EmployeeDetails(Employee user) {
		this.user = user;
	}

	public Employee getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		Set<EmployeeModule> modules = user.getRole().getModules();
		for (EmployeeModule employeeModule : modules) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + employeeModule.getModuleName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getEmail();
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
