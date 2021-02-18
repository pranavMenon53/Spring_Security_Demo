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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

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
      .antMatchers("/", "index", "/css/*", "/js/*")
      .permitAll()
      .antMatchers("/api/**")
      .hasAuthority("ROLE_" + STUDENT.name()) // ROLE_STUDENT is how spring saves role names
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
