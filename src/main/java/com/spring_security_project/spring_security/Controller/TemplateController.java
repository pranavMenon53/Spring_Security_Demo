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
    return "login";
  }
}
