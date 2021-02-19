package com.spring_security_project.spring_security.Auth;

import java.util.Optional;

public interface ApplicationUserDAO {
  Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
