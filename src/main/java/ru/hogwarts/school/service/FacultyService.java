package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Student> facultyMap =new HashMap<>();
    private long count=0;

    public Student addFaculty(Student student){
        student.setId(count++);
        facultyMap.put(student.getId(),student);
        return student;

    }

    public Student findFaculty(long id){
        return facultyMap.get(id);
    }
    public Student editFaculty(Student student){
        if (!facultyMap.containsKey(student.getId())){
            return null;
        }
        facultyMap.put(student.getId(), student);
        return student;
    }
    public Student deleteFaculty(long id){
        return facultyMap.remove(id);
    }
}
