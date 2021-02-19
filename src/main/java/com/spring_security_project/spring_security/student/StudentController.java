package com.spring_security_project.spring_security.student;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

  private static List<Student> studentList = Arrays.asList(
    new Student(1, "Purnima"),
    new Student(2, "Pranav"),
    new Student(3, "Amy"),
    new Student(4, "Jake")
  );

  @GetMapping(path = "{studentId}")
  public Student getStudent(@PathVariable("studentId") Integer studentId) {
    return studentList
      .stream()
      .filter(student -> studentId.equals(student.getStudentId()))
      .findFirst()
      .orElseThrow(
        () ->
          new IllegalStateException(
            "Student with id : \'" + studentId + "\' does not exist."
          )
      );
  }
}
