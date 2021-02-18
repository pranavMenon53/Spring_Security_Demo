package com.spring_security_project.spring_security.student;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

  private List<Student> studentList = Arrays.asList(
    new Student(1, "Purima"),
    new Student(2, "Pranav"),
    new Student(3, "Amy"),
    new Student(4, "Jake")
  );

  // @PreAuthorize is for providing PERMISSION BASED AUTHENTICATION using annotations
  // It accepts a String
  // Arguments can be like - hasRole('ROLE_STUDENT') hasAnyRole('') hasAuthority('') hasAnyAuthority('')
  @GetMapping
  @PreAuthorize("hasAuthority('student:read')")
  public List<Student> getStudentList() {
    System.out.println("\n\nGET req!\n\n");
    return this.studentList;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('student:write')")
  public void registerStudent(@RequestBody Student student) {
    System.out.println("\n\nRegistering : " + student.toString() + "\n\n");
  }

  @DeleteMapping(path = "/{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    System.out.println(
      "\n\nDeleting : " +
      studentId +
      " , " +
      studentList.get(studentId - 1) +
      "\n\n"
    );
  }

  @PutMapping(path = "/{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void updateStudent(
    @PathVariable("studentId") Integer studentId,
    @RequestBody Student student
  ) {
    System.out.println(
      "\n\nUpdating : " + studentId + " , " + student.toString() + "\n\n"
    );
  }
}
