package com.neosoft.spring.security.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet(ApplicationUserPermission.INSERT)), ADMIN(Sets.newHashSet(ApplicationUserPermission.INSERT,
			ApplicationUserPermission.GET_All, ApplicationUserPermission.GET_STUDENT_BY_ID));

	private final Set<ApplicationUserPermission> permission;

	private ApplicationUserRole(Set<ApplicationUserPermission> permission) {
		this.permission = permission;
	}

	public Set<ApplicationUserPermission> getPermission() {
		return permission;
	}

	public Set<SimpleGrantedAuthority> grantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermission().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
