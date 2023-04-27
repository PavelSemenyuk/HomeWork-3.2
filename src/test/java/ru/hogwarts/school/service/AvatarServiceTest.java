package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.repositories.AvatarRepository;

@ContextConfiguration(classes = {AvatarService.class})
@ExtendWith(SpringExtension.class)
public class AvatarServiceTest {
    @Autowired
    public AvatarService avatarService;
    @MockBean
    AvatarRepository avatarRepository;

    @Test
    public void uploadAvatar() {
        //вводные


        //действие


        // проверка


        // проверка мока, что вызывался метод сейв

    }

    @Test
    public void findAvatar() {
        //вводные


        //действие


        // проверка


        // проверка мока, что вызывался метод сейв


    }

    @Test
    public void getExtensions() {
        //вводные


        //действие


        // проверка



        // проверка мока, что вызывался метод сейв

    }

}
