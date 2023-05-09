package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultytRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FacultyService.class})
@ExtendWith(SpringExtension.class)
public class FacultyServiceTest {
    @Autowired
    public FacultyService facultyService;

    @MockBean
    FacultytRepository facultytRepository;

    @Test
    public void addStudentTest() {
        //вводные
        Faculty faculty = new Faculty(2L, "Ivan", "красный");

        //действие
        when(facultytRepository.save(faculty)).thenReturn(faculty);

        // проверка
        Assertions.assertEquals(faculty, facultyService.addFaculty(faculty));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(facultytRepository).save(faculty);
    }


    @Test
    public void editStudentTest() {
        //вводные
        Faculty faculty = new Faculty(1L, "Ivan", "розовый");

        //действие
        when(facultytRepository.save(faculty)).thenReturn(faculty);

        // проверка
        Assertions.assertEquals(faculty, facultyService.editFaculty(faculty));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(facultytRepository).save(faculty);
    }

    @Test
    public void deleteStudentTest() {
        //вводные
        Long id = 2L;

        //действие
        facultyService.deleteFaculty(id);

        // проверка

        // проверка мока, что вызывался метод делит
        Mockito.verify(facultytRepository).deleteById(id);
    }


    @Test
    public void getAllTest() {
        //вводные
        List<Faculty> facultyCollection = new ArrayList<>();
        Faculty faculty2 = new Faculty(1L, "Ivan", "синий");
        Faculty faculty3 = new Faculty(1L, "Petr", "черный");
        facultyCollection.add(faculty2);
        facultyCollection.add(faculty3);

        //действие
        when(facultytRepository.findAll()).thenReturn(facultyCollection);

        // проверка
        Assertions.assertEquals(facultyCollection, facultyService.getAll());

        // проверка мока, что вызывался метод сейв
        Mockito.verify(facultytRepository).findAll();
    }

    @Test
    public void findByColorTest() {
        //вводные
        List<Faculty> facultyCollection = new ArrayList<>();
        Faculty faculty2 = new Faculty(1L, "Ivan", "синий");
        facultyCollection.add(faculty2);

        //действие
        when(facultytRepository.findByColor(faculty2.getColor())).thenReturn(facultyCollection);

        // проверка
        Assertions.assertEquals(facultyCollection, facultyService.findByColor(faculty2.getColor()));

        // проверка мока, что вызывался метод сейв
        Mockito.verify(facultytRepository).findByColor(faculty2.getColor());
    }


}
