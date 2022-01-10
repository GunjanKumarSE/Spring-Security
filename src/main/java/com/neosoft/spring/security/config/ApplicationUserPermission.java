package com.neosoft.spring.security.config;

public enum ApplicationUserPermission {
//	STUDENT_READ("student:read"), STUDENT_WRITE("student:write"), COURSE_READ("course:read"),
//	COURSE_WRITE("course:write");

	INSERT("insert"), GET_All("get:all"), GET_STUDENT_BY_ID("get:student:by:id");

	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
