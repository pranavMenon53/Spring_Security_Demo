package com.spring_security_project.spring_security.student;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

  private List<Student> studentList = Arrays.asList(
    new Student(1, "Purima"),
    new Student(2, "Pranav"),
    new Student(3, "Amy"),
    new Student(4, "Jake")
  );

  @GetMapping
  public List<Student> getStudentList() {
    System.out.println("\n\nGET REQ!\n\n");
    return this.studentList;
  }

  @PostMapping
  public void registerStudent(@RequestBody Student student) {
    System.out.println("\n\nRegistering : " + student.toString() + "\n\n");
  }

  @DeleteMapping(path = "/{studentId}")
  public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    System.out.println("\n\nRemoving : " + studentId + "\n\n");
  }

  @PutMapping(path = "/{studentId}")
  public void updateStudent(
    @PathVariable("studentId") Integer studentId,
    @RequestBody Student student
  ) {
    System.out.println(
      "Updating : " + studentId + " , " + student.toString() + "\n\n"
    );
  }
}
