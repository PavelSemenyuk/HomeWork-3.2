package ru.hogwarts.school;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Stream;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {
    @Autowired
    public StudentService studentService;

//    @MockBean
//    StudentRepository studentRepository;

    public static Stream<Arguments> argument_addStudent() {
        return Stream.of(
                Arguments.of(1, "Ivan", 23,
                        new Student(1, "Ivan", 23)),
                Arguments.of(2, "Petr", 27,
                        new Student(2, "Petr", 27))
        );
    }


    public static Stream<Arguments> argument_findStudent() {
        return Stream.of(
                Arguments.of(1, "Ivan", 23,
                        new Student(1, "Ivan", 23)),
                Arguments.of(2, "Petr", 27,
                        new Student(2, "Petr", 27))
        );
    }


    public static Stream<Arguments> argument_editStudent() {
        return Stream.of(
                Arguments.of(1, "Ivan", 23,
                        new Student(1, "Ivan", 23)),
                Arguments.of(2, "Petr", 27,
                        new Student(2, "Petr", 27))
        );
    }


    public static Stream<Arguments> argument_deleteStudent() {
        return Stream.of(
                Arguments.of(1, "Ivan", 23,
                        new Student(1, "Ivan", 23)),
                Arguments.of(2, "Petr", 27,
                        new Student(2, "Petr", 27))
        );
    }


    public static Stream<Arguments> argument_getAll() {
        return Stream.of(
                Arguments.of(new Student(1, "Ivan", 23),
                        new Student(2, "Petr", 27)));
    }

    @ParameterizedTest
    @MethodSource("argument_addStudent")
    void addStudentTest(long id, String name, int age, Student expectedResult) {
        Student student1 = new Student(id, name, age);
        Assertions.assertEquals(studentService.addStudent(student1), expectedResult);

    }

    @ParameterizedTest
    @MethodSource("argument_findStudent")
    void findStudentTest(Long id, String name, int age, Student expectedResult) {
        Student actual = new Student(id, name, age);
        studentService.addStudent(actual);
        Assertions.assertEquals(studentService.findStudent(actual.getId()), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_editStudent")
    void editStudentTest(long id, String name, int age, Student expectedResult) {
        Student student2 = new Student(id, name, age);
        studentService.addStudent(student2);
        Assertions.assertEquals(studentService.editStudent(student2), expectedResult);
    }


    @ParameterizedTest
    @MethodSource("argument_deleteStudent")
    void deleteStudentTest(long id, String name, int age, Student expectedResult) {
        Student student3 = new Student(id, name, age);
        studentService.addStudent(student3);
        // Assertions.assertEquals(studentService.deleteStudent(id), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_getAll")
    void getAllTest(Collection<Student> expectedResult) {
        Assertions.assertEquals(studentService.getAll(), expectedResult);

    }
}
