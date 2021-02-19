package com.spring_security_project.spring_security.Auth;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class ApplicationUserService implements UserDetailsService {

//   private final ApplicationUserDAO applicationUserDAO;

//   @Autowired
//   public ApplicationUserService(
//     @Qualifier("fake") ApplicationUserDAO applicationUserDAO
//   ) {
//     this.applicationUserDAO = applicationUserDAO;
//   }

//   @Override
//   public UserDetails loadUserByUsername(String username)
//     throws UsernameNotFoundException {
//     return applicationUserDAO
//       .selectApplicationUserByUsername(username)
//       .orElseThrow(
//         () ->
//           new UsernameNotFoundException("Username : " + username + " Not Found")
//       );
//   }
// }
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

  private final ApplicationUserDAO applicationUserDAO;

  @Autowired
  public ApplicationUserService(
    @Qualifier("fake") ApplicationUserDAO applicationUserDAO
  ) {
    this.applicationUserDAO = applicationUserDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    System.out.println("\n\nin loadUserByUsername!\n\n");
    return applicationUserDAO
      .selectApplicationUserByUsername(username.strip())
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            String.format("Username %s not found", username)
          )
      );
  }
}
