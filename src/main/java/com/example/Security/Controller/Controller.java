package com.example.Security.Controller;

import com.example.Security.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    ArrayList<Student> students =  new ArrayList<>(List.of(

            new Student(1, "Rahul", 77),
            new Student(2, "Adam", 77),
            new Student(3, "Sara", 77)

    ));

    @GetMapping("/")
    public String homePage(HttpServletRequest request){
        System.out.println("in home page :session id:"+request.getSession().getId());
        return "Welcome :sessio id:"+ request.getSession().getId();
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken)request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody Student student){
            students.add(student);
    return students;
    }
}
