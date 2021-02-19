package com.spring_security_project.spring_security.Auth;

import static com.spring_security_project.spring_security.security.ApplicationUserRoles.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDAO {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  Optional<ApplicationUser> findUser(
    List<ApplicationUser> tempList,
    String username
  ) {
    System.out.println("\nIn Find User!\n");
    for (int i = 0; i < tempList.size(); i++) {
      if (username.equals(tempList.get(i).getUsername())) {
        return Optional.of(tempList.get(i));
      }
      System.out.println(
        "" +
        tempList.get(i).getUsername() +
        " not equal to : " +
        username +
        "\n"
      );
    }
    return Optional.empty();
  }

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(
    String username
  ) {
    System.out.println("\n\nin selectApplicationUserByUsername!\n\n");
    List<ApplicationUser> tempList = getApplicationUsers();
    Optional<ApplicationUser> user = findUser(tempList, username);
    //   .stream()
    //   .filter(applicationUser -> username.equals(applicationUser.getUsername()))
    //   .findFirst();
    if (user.isEmpty()) {
      // System.out.println("\n\n\n\n");
      System.out.println("\n\nNO USER FOUND\n\n");
    } else {
      System.out.println("\n\n" + user.get().toString() + "\n\n");
    }
    return user;
  }

  // Get data from the data source
  // Here, the DataSource is static
  private List<ApplicationUser> getApplicationUsers() {
    List<ApplicationUser> applicationUsers = Arrays.asList(
      new ApplicationUser(
        STUDENT.getGrantedAuthorities(),
        passwordEncoder.encode("password"),
        "David",
        true,
        true,
        true,
        true
      ),
      new ApplicationUser(
        ADMIN.getGrantedAuthorities(),
        passwordEncoder.encode("password123"),
        "Bob",
        true,
        true,
        true,
        true
      ),
      new ApplicationUser(
        TRAINEE.getGrantedAuthorities(),
        passwordEncoder.encode("password123"),
        "Tom",
        true,
        true,
        true,
        true
      )
    );
    return applicationUsers;
  }
}
//
// import com.google.common.collect.Lists;
// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Repository;
// @Repository("fake")
// public class FakeApplicationUserDaoService implements ApplicationUserDAO {
//   private final PasswordEncoder passwordEncoder;
//   @Autowired
//   public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
//     this.passwordEncoder = passwordEncoder;
//   }
//   Optional<ApplicationUser> findUser(
//     List<ApplicationUser> tempList,
//     String username
//   ) {
//     System.out.println("\nIn Find User!\n");
//     for (int i = 0; i < tempList.size(); i++) {
//       if (username.equals(tempList.get(i).getUsername())) {
//         return Optional.of(tempList.get(i));
//       }
//       System.out.println(
//         "" +
//         tempList.get(i).getUsername() +
//         " not equal to : " +
//         username +
//         "\n"
//       );
//     }
//     return Optional.empty();
//   }
//   @Override
//   public Optional<ApplicationUser> selectApplicationUserByUsername(
//     String username
//   ) {
//     System.out.println("\n\nin selectApplicationUserByUsername!\n\n");
//     List<ApplicationUser> tempList = getApplicationUsers();
//     Optional<ApplicationUser> user = findUser(tempList, username);
//     //   .stream()
//     //   .filter(applicationUser -> username.equals(applicationUser.getUsername()))
//     //   .findFirst();
//     if (user.isEmpty()) {
//       // System.out.println("\n\n\n\n");
//       System.out.println("\n\nNO USER FOUND\n\n");
//     } else {
//       System.out.println("\n\n" + user.get().toString() + "\n\n");
//     }
//     return user;
//   }
//   private List<ApplicationUser> getApplicationUsers() {
//     List<ApplicationUser> applicationUsers = Lists.newArrayList(
//       new ApplicationUser(
//         STUDENT.getGrantedAuthorities(),
//         passwordEncoder.encode("password"),
//         "David",
//         true,
//         true,
//         true,
//         true
//       ),
//       new ApplicationUser(
//         ADMIN.getGrantedAuthorities(),
//         passwordEncoder.encode("password123"),
//         "Bob",
//         true,
//         true,
//         true,
//         true
//       ),
//       new ApplicationUser(
//         TRAINEE.getGrantedAuthorities(),
//         passwordEncoder.encode("password123"),
//         "Tom",
//         true,
//         true,
//         true,
//         true
//       )
//     );
//     System.out.println("\n\nUser List is generated!\n\n");
//     return applicationUsers;
//   }
// }
