package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserRoles.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
// prePostEnabled is set to true inorder to aloow us to make use of @PreAuthorize()
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // NOTE - Order of antMatchers really matters!
    http
      .csrf()
      .disable()
      .authorizeRequests()
      //
      .antMatchers("/", "index", "/css/*", "/js/*")
      .permitAll()
      //Only STUDENT can access this API
      .antMatchers("/api/**")
      //
      .hasAuthority("ROLE_" + STUDENT.name()) // ROLE_STUDENT is how we save role names
      //The antmatchers below are replaced by Annotations in "StudentManagementController.java"
      // .antMatchers(HttpMethod.DELETE, "/management/api/**")
      // .hasAnyAuthority(COURSE_WRITE.getPermission())
      // .antMatchers(HttpMethod.POST, "/management/api/**")
      // .hasAnyAuthority(COURSE_WRITE.getPermission())
      // .antMatchers(HttpMethod.PUT, "/management/api/**")
      // .hasAnyAuthority(COURSE_WRITE.getPermission())
      // .antMatchers(HttpMethod.GET, "/management/api/**")
      // .hasAnyAuthority(COURSE_READ.getPermission())
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
      // STUDENT comes from ApplicationUserRoles enum
      .authorities(STUDENT.getGrantedAuthorities())
      // .roles(STUDENT.name())
      .build();

    UserDetails adminUser = User
      .builder()
      .username("Bob")
      .password(passwordEncoder.encode("password123"))
      // ADMIN comes from ApplicationUserRoles enum
      .authorities(ADMIN.getGrantedAuthorities())
      // .roles(ADMIN.name())
      .build();

    UserDetails traineeUser = User
      .builder()
      .username("Tom")
      .password(passwordEncoder.encode("password123"))
      // TRAINEE comes from ApplicationUserRoles enum
      .authorities(TRAINEE.getGrantedAuthorities())
      // .roles(TRAINEE.name())
      .build();

    return new InMemoryUserDetailsManager(user, adminUser, traineeUser);
  }
}
//
// The blocks of code below are used to implement ROLE BASED or PERMISSION BASED AUTHENTICATION
// We can also do this by using Annotations.
// Refer to "StudentManagementController.java"  for more information
//
// Use the block below in "configure" method for ROLE BASED AUTHENTICATION
// Change the User configuration in "userDetailsService" accordingly
/*
        //
        .antMatchers("/api/**")
        .hasRole(STUDENT.name())
        //
        .antMatchers(HttpMethod.DELETE, "/management/api/**")
        .hasAnyRole(ADMIN.name(), TRAINEE.name())
        //
        .antMatchers(HttpMethod.POST, "/management/api/**")
        .hasAnyRole(ADMIN.name(), TRAINEE.name())
        //
        .antMatchers(HttpMethod.PUT, "/management/api/**")
        .hasAnyRole(ADMIN.name(), TRAINEE.name())
        //
        .antMatchers(HttpMethod.GET, "/management/api/**")
        .hasAnyRole(ADMIN.name(), TRAINEE.name())
        //
      */
// Use the block below in "configure" method for PERMISSION BASED AUTHENTICATION
// Change the User configuration in "userDetailsService" accordingly
/*
         //Only STUDENT can access this API
        .antMatchers("/api/**")
        //
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
        //
      */
//
