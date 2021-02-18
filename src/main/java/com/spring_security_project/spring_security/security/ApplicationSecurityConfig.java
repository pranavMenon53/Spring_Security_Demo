package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserRoles.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
      .authorizeRequests()
      .antMatchers("/", "index", "/home", "/css/*", "/js/*")
      .permitAll()
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
      //   .password("Pas$WorD")
      .password(passwordEncoder.encode("Pas$WorD"))
      .roles(STUDENT.name()) // STUDENT comes from ApplicationUserRoles enum
      .build();

    UserDetails adminUser = User
      .builder()
      .username("Bob")
      .password(passwordEncoder.encode("password123"))
      .roles(ADMIN.name()) // ADMIN comes from ApplicationUserRoles enum
      .build();

    return new InMemoryUserDetailsManager(user, adminUser);
  }
}
