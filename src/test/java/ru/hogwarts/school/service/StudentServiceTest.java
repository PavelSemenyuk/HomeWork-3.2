package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {
    @Autowired
    public StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    @Test
    public void addStudentTest() {
        //вводные
        Student student = new Student(1, "Ivan", 23);

        //действие
        when(studentRepository.save(student)).thenReturn(student);

        // проверка
        Assertions.assertEquals(student, studentService.addStudent(student));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(studentRepository).save(student);
    }


    @Test
    public void editStudentTest() {
        //вводные
        Student student = new Student(1, "Ivan", 23);

        //действие
        when(studentRepository.save(student)).thenReturn(student);

        // проверка
        Assertions.assertEquals(student, studentService.editStudent(student));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(studentRepository).save(student);
    }

    @Test
    public void deleteStudentTest() {
        //вводные
        Long id = 2L;

        //действие
        studentService.deleteStudent(id);

        // проверка

        // проверка мока, что вызывался метод делит
        Mockito.verify(studentRepository).deleteById(id);
    }


    @Test
    public void getAllTest() {
        //вводные
        List<Student> studentCollection = new ArrayList<>();
        Student student2 = new Student(1, "Ivan", 23);
        Student student3 = new Student(1, "Petr", 33);
        studentCollection.add(student2);
        studentCollection.add(student3);

        //действие
        when(studentRepository.findAll()).thenReturn(studentCollection);

        // проверка
        Assertions.assertEquals(studentCollection, studentService.getAll());

        // проверка мока, что вызывался метод сейв
        Mockito.verify(studentRepository).findAll();
    }

    @Test
    public void findByAgeBetweenTest() {
        //вводные
        List<Student> studentCollection = new ArrayList<>();
        Student student2 = new Student(1, "Ivan", 23);
        Student student3 = new Student(1, "Petr", 33);
        studentCollection.add(student2);
        studentCollection.add(student3);
        int age = 20;
        int age2 = 50;

        //действие
        when(studentRepository.findByAgeBetween(age, age2)).thenReturn(studentCollection);

        // проверка
        Assertions.assertEquals(studentCollection, studentService.findByAgeBetween(age, age2));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(studentRepository).findByAgeBetween(age, age2);

    }


}
