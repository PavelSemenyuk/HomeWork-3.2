package ru.hogwarts.school.сontroller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultytRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private FacultyService facultyService;
    @SpyBean
    private StudentService studentService;
    @SpyBean
    AvatarService avatarService;
    @InjectMocks
    private StudentController studentController;


    @Test
    public void saveStudentTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 21;
        final String name = "Anna";
        final int age = 29;

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод сейв из репозитория с любым (any) студентом(Student.class). Тогда мы вернем нашего конкретного студента которого создали
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));


        // Обращаемся к нашей переменной mockMvc и выполняем запрос  при помощи метода perform. MockMvcRequestBuilders Для того чтоб сделать правильный запрос
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")    // указываем Url - необходимый для нас запрос
                        .content(studentObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)    //  передаем тело в виде джейсона
                        .accept(MediaType.APPLICATION_JSON)) //   в ответ принимаем тоже джейсон

                // проверяем что возврашается
                .andExpect(status().isOk())       // andExpect- проверяетс все ли норм!? status().isOk()- Получаем ли мы статус 200
                .andExpect(jsonPath("$.id").value(id)) // используем спец. класс jsonPath и  проверяем что в джейсоне есть  в данном случае id. value - убеждается что он есть
                .andExpect(jsonPath("$.name").value(name)) // используем спецю класс jsonPath и проверяем что в джейсоне есть  в данном случае name.  value - убеждается что он есть
                .andExpect(jsonPath("$.age").value(age));  // используем спецю класс jsonPath и проверяем что в джейсоне есть  в данном случае age.  value - убеждается что он есть


        // выполним так же ГЕТ запрос
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON)) // т к мы нечего не передаем а только проверяем то используем только один джейсон
                .andExpect(status().isOk())     //  проверяетс все ли норм!?
                .andExpect(jsonPath("$.id").value(id))    // ---
                .andExpect(jsonPath("$.name").value(name))   //  ---
                .andExpect(jsonPath("$.age").value(age));     //   ---
    }


    @Test
    public void deleteStudentTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 21;
        final String name = "Anna";
        final int age = 29;

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student" + id)
                        .content(studentObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllStudentTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 21;
        final String name = "Anna";
        final int age = 29;


        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод сейв из репозитория с любым (any) студентом(Student.class). Тогда мы вернем нашего конкретного студента которого создали
        when(studentService.getAll()).thenReturn(Collections.emptyList());
        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void putStudentTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 21;
        final String name = "Anna";
        final int age = 29;

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод editStudent из сервиса с студентом. Тогда мы вернем нашего конкретного студента которого создали
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())    //  передаем тело запроса в виде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))    // ---
                .andExpect(jsonPath("$.name").value(name))   //  ---
                .andExpect(jsonPath("$.age").value(age));

    }
}
