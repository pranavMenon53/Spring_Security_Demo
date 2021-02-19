package com.spring_security_project.spring_security.Auth;

import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails {

  private final Set<? extends GrantedAuthority> authorities;
  private final String password;
  private final String username;
  private final boolean isAccountNonExpired;
  private final boolean isAccountNonLocked;
  private final boolean isCredentialsNonExpired;
  private final boolean isEnabled;

  public ApplicationUser(
    Set<? extends GrantedAuthority> authorities,
    String password,
    String username,
    boolean isAccountNonExpired,
    boolean isAccountNonLocked,
    boolean isCredentialsNonExpired,
    boolean isEnabled
  ) {
    this.authorities = authorities;
    this.password = password;
    this.username = username;
    this.isAccountNonExpired = isAccountNonExpired;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isCredentialsNonExpired = isCredentialsNonExpired;
    this.isEnabled = isEnabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }

  @Override
  public String toString() {
    return (
      "\n\n{" +
      " authorities='" +
      getAuthorities() +
      "'" +
      ", password='" +
      getPassword() +
      "'" +
      ", username='" +
      getUsername() +
      "'" +
      ", isAccountNonExpired='" +
      isAccountNonExpired() +
      "'" +
      ", isAccountNonLocked='" +
      isAccountNonLocked() +
      "'" +
      ", isCredentialsNonExpired='" +
      isCredentialsNonExpired() +
      "'" +
      ", isEnabled='" +
      isEnabled() +
      "'" +
      "}\n\n"
    );
  }
}
