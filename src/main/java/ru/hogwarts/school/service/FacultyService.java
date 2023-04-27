package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultytRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class FacultyService {

  private final FacultytRepository facultytRepository;

    public FacultyService(FacultytRepository facultytRepository) {
        this.facultytRepository = facultytRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultytRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        Optional<Faculty> facultyForFind = facultytRepository.findById(id);
        Faculty facultyFind = facultyForFind.get();
        facultyFind.setName();
        facultyFind.setColor();
        return facultyFind;
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultytRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultytRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        return facultytRepository.findAll();
    }
    public Collection<Faculty> findByColor(String color) {
        return facultytRepository.findByColor(color);
    }





//    public Faculty findByName(String name){
//        return facultytRepository.findByNameContainsIgnoreCase(name);
//    }
//
//    public Collection<Faculty> findByIdIn(long id){
//        return facultytRepository.findByIdIs(id);
//    }
}