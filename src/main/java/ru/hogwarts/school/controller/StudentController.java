
package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student createSt = studentService.addStudent(student);
        return ResponseEntity.ok(createSt).getBody();
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> findStudentsFaculty(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findStudent(id).getFaculty());
    }


   @GetMapping("/Count")
    public long getAllCount(){
       return studentService.getAllCount();
   }

    @GetMapping("/average-age")
    public double getAverageAge(){
        return studentService.getAverageAge();
    }

    @GetMapping("/student-last-fave")
    public List<Student> getStudentLastFave(){
        return studentService.getStudentLastFave();
    }


}
