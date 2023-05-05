package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultytRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultytRepository facultytRepository;

    public FacultyService(FacultytRepository facultytRepository) {
        this.facultytRepository = facultytRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Был вызван метод для создания факультета");
        return facultytRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        Optional<Faculty> facultyForFind = facultytRepository.findById(id);
        if (facultyForFind.isEmpty()) {
            logger.error("Нет такого факультета c id = " + id);
            throw new RuntimeException("Нет такого факультета");
        }
        return facultyForFind.get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Был вызван метод для изменения факультета");
        return facultytRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Был вызван метод для удаления факультета");
        facultytRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        logger.info("Был вызван метод для получения списка всех факультетов");
        return facultytRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Был вызван метод для получения цвета факультета");
        return facultytRepository.findByColor(color);
    }

    public Collection<Faculty> getByColorAndName(String color, String name) {
        logger.info("Был вызван метод для получения названия и цвета факультета");
        return facultytRepository.getByColorAndName(color, name);
    }

    public String getLongestNameOfFaculty() {
        Optional<String> longName = Optional.of(facultytRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length)).orElse(""));

        logger.info("Был вызван метод для получения самого длинного названия факультета");
        return longName.orElse("");
    }


}