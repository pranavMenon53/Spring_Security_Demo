package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserPermissions.*;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRoles {
  STUDENT(Sets.newHashSet()),
  ADMIN(
    Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)
  ),
  TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

  private final Set<ApplicationUserPermissions> permission;

  private ApplicationUserRoles(Set<ApplicationUserPermissions> permission) {
    this.permission = permission;
  }

  public Set<ApplicationUserPermissions> getPermissions() {
    return permission;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions()
      .stream()
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toSet());

    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
