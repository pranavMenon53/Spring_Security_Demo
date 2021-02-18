package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserPermissions.*;
import static com.spring_security_project.spring_security.security.ApplicationUserRoles.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .authorizeRequests()
      //
      .antMatchers("/", "index", "/css/*", "/js/*")
      .permitAll()
      //Only STUDENT can access this API
      .antMatchers("/api/**")
      // .hasRole(STUDENT.name())
      .hasAuthority("ROLE_" + STUDENT.name()) // ROLE_STUDENT is how we save role names
      //
      .antMatchers(HttpMethod.DELETE, "/management/api/**")
      .hasAnyAuthority(COURSE_WRITE.getPermission())
      //
      .antMatchers(HttpMethod.POST, "/management/api/**")
      .hasAnyAuthority(COURSE_WRITE.getPermission())
      //
      .antMatchers(HttpMethod.PUT, "/management/api/**")
      .hasAnyAuthority(COURSE_WRITE.getPermission())
      //
      .antMatchers(HttpMethod.GET, "/management/api/**")
      .hasAnyAuthority(COURSE_READ.getPermission())
      // .hasAnyRole(ADMIN.name(), TRAINEE.name())
      //
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
  }

  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails user = User
      .builder()
      .username("David")
      .password(passwordEncoder.encode("password"))
      .authorities(STUDENT.getGrantedAuthorities())
      // .roles(STUDENT.name()) // STUDENT comes from ApplicationUserRoles enum
      .build();

    UserDetails adminUser = User
      .builder()
      .username("Bob")
      .password(passwordEncoder.encode("password123"))
      .authorities(ADMIN.getGrantedAuthorities())
      // .roles(ADMIN.name()) // ADMIN comes from ApplicationUserRoles enum
      .build();

    UserDetails traineeUser = User
      .builder()
      .username("Tom")
      .password(passwordEncoder.encode("password"))
      .authorities(TRAINEE.getGrantedAuthorities())
      // .roles(TRAINEE.name())
      .build();

    return new InMemoryUserDetailsManager(user, adminUser, traineeUser);
  }
}
