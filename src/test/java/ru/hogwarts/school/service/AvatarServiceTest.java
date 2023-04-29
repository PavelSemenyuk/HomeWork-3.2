package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.FacultytRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AvatarService.class})
@ExtendWith(SpringExtension.class)
public class AvatarServiceTest {
    @Autowired
    public AvatarService avatarService;
    @MockBean
    private AvatarRepository avatarRepository;
    @MockBean
    private FacultytRepository facultytRepository;


    @Test
    void getAllTest() {
        //Подготовка входных данных
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);

        Avatar firstAvatar = new Avatar();
        firstAvatar.setId(1L);

        Avatar secondAvatar = new Avatar();
        secondAvatar.setId(2L);

        List<Avatar> avatarList = List.of(firstAvatar, secondAvatar);

        PageImpl<Avatar> pageable = new PageImpl<>(avatarList);

        //Подготовка ожидаемого результата
        when(avatarRepository.findAll(pageRequest)).thenReturn(pageable);

        //Начало теста
        List<Avatar> actualAvatar = avatarService.getAll(page, size);
        Assertions.assertEquals(avatarList, actualAvatar);
        verify(avatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(avatarRepository);

    }

    @Test
    void getAll_empty() {
        //Подготовка входных данных
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);

        //Подготовка ожидаемого результата
        when(avatarRepository.findAll(pageRequest)).thenReturn(Page.empty());

        //Начало теста
        List<Avatar> actualAvatars = avatarService.getAll(page, size);
        Assertions.assertTrue(actualAvatars.isEmpty());
        verify(avatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(avatarRepository);
    }

}
