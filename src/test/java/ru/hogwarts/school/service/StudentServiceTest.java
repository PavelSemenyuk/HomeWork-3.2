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


//    public static Stream<Arguments> argument_addStudent() {
//        return Stream.of(
//                Arguments.of(1, "Ivan", 23,
//                        new Student(1, "Ivan", 23)),
//                Arguments.of(2, "Petr", 27,
//                        new Student(2, "Petr", 27))
//        );
//    }
//
//    public static Stream<Arguments> argument_findStudent() {
//        return Stream.of(
//                Arguments.of(1, "Ivan", 23,
//                        new Student(1, "Ivan", 23)),
//                Arguments.of(2, "Petr", 27,
//                        new Student(2, "Petr", 27))
//        );
//    }
//    public static Stream<Arguments> argument_editStudent() {
//        return Stream.of(
//                Arguments.of(1, "Ivan", 23,
//                        new Student(1, "Ivan", 23)),
//                Arguments.of(2, "Petr", 27,
//                        new Student(2, "Petr", 27))
//        );
//    }
//    public static Stream<Arguments> argument_deleteStudent() {
//        return Stream.of(
//                Arguments.of(1, "Ivan", 23,
//                        new Student(1, "Ivan", 23)),
//                Arguments.of(2, "Petr", 27,
//                        new Student(2, "Petr", 27))
//        );
//    }
//    public static Stream<Arguments> argument_getAll() {
//        return Stream.of(
//                Arguments.of(new Student(1, "Ivan", 23),
//                        new Student(2, "Petr", 27)));
//    }
//
//    @ParameterizedTest
//    @MethodSource("argument_addStudent")
//    void addStudentTest(long id, String name, int age, Student expectedResult) {
//        Student student1 = new Student(id, name, age);
//        Assertions.assertEquals(studentService.addStudent(student1), expectedResult);
//    }
//
//    @ParameterizedTest
//    @MethodSource("argument_findStudent")
//    void findStudentTest(Long id, String name, int age, Student expectedResult) {
//        Student actual = new Student(id, name, age);
//        studentService.addStudent(actual);
//        Assertions.assertEquals(studentService.findStudent(actual.getId()), expectedResult);
//    }
//    @ParameterizedTest
//    @MethodSource("argument_editStudent")
//    void editStudentTest(long id, String name, int age, Student expectedResult) {
//        Student student2 = new Student(id, name, age);
//        studentService.addStudent(student2);
//        Assertions.assertEquals(studentService.editStudent(student2), expectedResult);
//    }
//    @ParameterizedTest
//    @MethodSource("argument_deleteStudent")
//    void deleteStudentTest(long id, String name, int age, Student expectedResult) {
//        Student student3 = new Student(id, name, age);
//        studentService.addStudent(student3);
//        // Assertions.assertEquals(studentService.deleteStudent(id), expectedResult);
//    }
//    @ParameterizedTest
//    @MethodSource("argument_getAll")
//    void getAllTest(Collection<Student> expectedResult) {
//        Assertions.assertEquals(studentService.getAll(), expectedResult);
//
//    }
}
