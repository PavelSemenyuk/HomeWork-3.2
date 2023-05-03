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
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultytRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultytRepository facultytRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;
    @SpyBean
    AvatarService avatarService;
    @InjectMocks
    private FacultyController facultyController;


    @Test
    public void saveFacultyTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 22;
        final String name = "ХимБио";
        final String color = "red";

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject facultyObject = new JSONObject();

        facultyObject.put("name", name);
        facultyObject.put("color", color);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод сейв из репозитория с любым (any) студентом(Student.class). Тогда мы вернем нашего конкретного студента которого создали
        when(facultytRepository.save(any(Faculty.class))).thenReturn(faculty);
        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(facultytRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));


        // Обращаемся к нашей переменной mockMvc и выполняем запрос  при помощи метода perform. MockMvcRequestBuilders Для того чтоб сделать правильный запрос
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")    // указываем Url - необходимый для нас запрос
                        .content(facultyObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)    //  передаем тело в виде джейсона
                        .accept(MediaType.APPLICATION_JSON)) //   в ответ принимаем тоже джейсон
                // проверяем что возврашается
                .andExpect(status().isOk());      // andExpect- проверяетс все ли норм!? status().isOk()- Получаем ли мы статус 200


        // выполним так же ГЕТ запрос
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON)) // т к мы нечего не передаем а только проверяем то используем только один джейсон
                .andExpect(status().isOk());   //  проверяетс все ли норм!?

    }


    @Test
    public void deleteFacultyTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 30;
        final String name = "ХимБио";
        final String color = "red";

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )
        when(facultytRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultytRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty" + id)
                        .content(facultyObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllFacultyTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 30;
        final String name = "ХимБио";
        final String color = "red";

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод сейв из репозитория с любым (any) студентом(Student.class). Тогда мы вернем нашего конкретного студента которого создали
        when(facultyService.getAll()).thenReturn(Collections.emptyList());
        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(facultytRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(facultyObject.toString())    //  передаем тело запроса в видде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void putFacultyTest() throws Exception {
        // создаем отдельные константы наших полей.
        final long id = 30;
        final String name = "ХимБио";
        final String color = "red";

        // создаем джейсон который будем отправлять(т.к. используем пост запрос). ДАННЫЕ КОТОРЫЙ ОТПРАВЛЯЕМ
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);


        // создаем обьект который мы ожидаем. ДАННЫЕ КОТОРЫЕ ВЕРНУТЬСЯ
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);


        // ВЫПОЛНЯЕМ ЗАПРОСЫ (БЕЗ ДАБАВЛЕНИЯ В БАЗУ Т.Е ПРОВЕРЯЕМ-ТЕСТИРУЕМ ТЕ ДАННЫЕ ЧТО МЫ УКАЗАЛИ ВЫШЕ )

        // метод when - когда мы вызываем метод save из сервиса с студентом. Тогда мы вернем нашего конкретного студента которого создали
        when(facultytRepository.save(any(Faculty.class))).thenReturn(faculty);

        // Обратное действие! когда мы вызываем метод findById из репозитория с любым (any) идентихикатором(Long.class). Тогда мы вернем новый опшинал от нашего студента которого создали.
        when(facultytRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())    //  передаем тело запроса в виде строки
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}