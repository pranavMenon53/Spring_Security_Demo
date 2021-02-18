package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserPermissions.*;

import com.google.common.collect.Sets;
import java.util.Set;

public enum ApplicationUserRoles {
  STUDENT(Sets.newHashSet()),
  ADMIN(
    Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)
  );

  private final Set<ApplicationUserPermissions> permission;

  private ApplicationUserRoles(Set<ApplicationUserPermissions> permission) {
    this.permission = permission;
  }

  public Set<ApplicationUserPermissions> getPermission() {
    return permission;
  }
}
