package com.spring_security_project.spring_security.security;

import static com.spring_security_project.spring_security.security.ApplicationUserRoles.*;

import com.spring_security_project.spring_security.Auth.ApplicationUserService;
import com.spring_security_project.spring_security.Auth.ApplicationUserService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// prePostEnabled is set to true inorder to aloow us to make use of @PreAuthorize()
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  private final ApplicationUserService applicationUserService;

  @Autowired
  public ApplicationSecurityConfig(
    PasswordEncoder passwordEncoder,
    ApplicationUserService applicationUserService
  ) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
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
      // .httpBasic();
      //Switching from basic auth to form based authentication
      .formLogin()
      .loginPage("/login")
      .permitAll()
      //We use the "passwordParameter" to look for a input with it's name set to the specified value
      //Now Spring Security will look for a parameter "password123" (look into login.html) and treat it's value as the password
      //Same is the case for "usernameParameter" and "rememberMeParameter"
      .passwordParameter("password123")
      .usernameParameter("usernamePQR")
      .and()
      .rememberMe()
      .userDetailsService(applicationUserService)
      .rememberMeParameter("remember-meXYZ")
      .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
      //Overiding the existing "KEY" provided by String to create a MD5 hash
      .key("Provide_Your_Custom_Secure_Key_Here!@#")
      .and()
      //Spring Provides a default "LOGOUT" handler at "/logout" url
      //Here, we specify out own logout handler that overrides the one provided by String
      .logout()
      .logoutUrl("/logout")
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
      // This ^ happens by default
      // When CSRF is enabled, we must use a POST reqest to logout and not a get
      // Commenting out "logoutRequestMatcher" will result in default behaviour
      .clearAuthentication(true)
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID", "remember-me")
      .logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);
    return provider;
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
