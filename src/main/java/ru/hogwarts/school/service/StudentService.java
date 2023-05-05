package ru.hogwarts.school.service;

import liquibase.pro.packaged.S;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.sound.sampled.Port;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Был вызван метод для создания студента");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        Optional<Student> studentForFind = studentRepository.findById(id);
        if (studentForFind.isEmpty()) {
            logger.error("Нет такого студента c id = " + id);
            throw new RuntimeException("Нет такого студента");
        }
        logger.info("Был вызван метод для поиска студента по id");
        return studentForFind.get();
    }

    public Student editStudent(Student student) {
        logger.info("Был вызван метод для изменения студента");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Был вызван метод для удаления студента");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Был вызван метод для получения списка всех студентов");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int age, int age2) {
        logger.info("Был вызван метод для поиска студентов определенного возраста");
        return studentRepository.findByAgeBetween(age, age2);
    }


    public long getAllCount() {
        logger.info("Был вызван метод для получения количества всех студентов");
        return studentRepository.count();
    }

    public double getAverageAge() {
        logger.info("Был вызван метод для получения среднего возраста всех студентов");
        return studentRepository.getAverageAge();
    }

    public List<Student> getStudentLastFave() {
        logger.info("Был вызван метод для получения среднего возраста всех студентов");
        return studentRepository.getStudentLastFave();
    }

    public Collection<Student> getStudentsByName(String name) {
        logger.info("Был вызван метод для получения из списка последних пяти студентов");
        return studentRepository.getStudentsByName(name);
    }

//    public Student getPort(String port) {
//        logger.debug("??????????: {}", port);
//        return
//    }


}
