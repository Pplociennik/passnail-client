package com.passnail.connect.service.impl;

import com.passnail.data.transfer.model.dto.LocalUserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 23:14
 * Project: passnail-client
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreatingNewLocalUser() {
        LocalUserDto userDto = Mockito.mock(LocalUserDto.class);

        userService.createNewLocalUser(userDto);
    }
}