package com.spring_security_project.spring_security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/")
public class TemplateController {

  @GetMapping("login")
  public String getLoginView() {
    System.out.println("\n\nIn Login Page\n\n");
    return "login";
  }

  @GetMapping("courses")
  public String getCoursesView() {
    System.out.println("\n\nIn Courses Page\n\n");
    return "courses";
  }
}
