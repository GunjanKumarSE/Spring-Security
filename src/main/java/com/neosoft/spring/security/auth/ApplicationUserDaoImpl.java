package com.neosoft.spring.security.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.neosoft.spring.security.config.ApplicationUserRole;

@Repository("repo")
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(ApplicationUserRole.STUDENT.grantedAuthorities(),
						passwordEncoder.encode("password"), "gunjan", true, true, true, true),
				new ApplicationUser(ApplicationUserRole.ADMIN.grantedAuthorities(), passwordEncoder.encode("password"),
						"sachin", true, true, true, true));

		return applicationUsers;
	}

}
