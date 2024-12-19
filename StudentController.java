package com.javalearning.spring.boot_rest_api.Controller;

import com.javalearning.spring.boot_rest_api.Bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "Deepak", "Gogikar");

        //return new ResponseEntity<>(student,HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header","Deepak")
                 .body(student);
    }
    //http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "bhargav", "pottidare"));
        students.add(new Student(2, "Bharath", "Attigadda"));
        students.add(new Student(3, "rohith", "Banda"));
        students.add(new Student(4, "sanketh", "kulkarni"));
        return ResponseEntity.ok(students);
    }

    //http://localhost:8080/students/{id}/{first-name}/{last-name}
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student= new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //http://localhost:8080/students/query?id=1&firstName=Deepak&lastName=Gogikar
    @GetMapping("query")
    public ResponseEntity<Student> getstudentRequestParam(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName) {
        Student student=new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot to handle http post request for creating new resource
    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    //Sprig boot handling http put request to updated existing resources
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);

    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId)
    {
        System.out.println(studentId);
        return ResponseEntity.ok("message Student Deleted Successfully!!");
    }
}
