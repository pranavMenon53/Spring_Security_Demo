package com.spring_security_project.spring_security.security;

public enum ApplicationUserPermissions {
  STUDENT_READ("student:read"),
  STUDENT_WRITE("student:write"),
  COURSE_READ("course:read"),
  COURSE_WRITE("course:write");

  private final String permission;

  ApplicationUserPermissions(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
  // public static void main(String[] args) {
  //   System.out.println(STUDENT_READ.permission);
  // }
}
