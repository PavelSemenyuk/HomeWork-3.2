package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {

        Optional<Student> studentForFind = studentRepository.findById(id);
        if(studentForFind.isEmpty()){
            throw new RuntimeException("Нет такого студента");
        }
        return studentForFind.get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int age, int age2) {
        return studentRepository.findByAgeBetween(age, age2);
    }



    public long getAllCount(){
        return  studentRepository.count();
    }

    public double getAverageAge(){
        return studentRepository.getAverageAge();
    }

    public List<Student> getStudentLastFave(){
        return studentRepository.getStudentLastFave();
    }

    public Collection<Student> getStudentsByName(String name){
        return studentRepository.getStudentsByName(name);
    }



}
