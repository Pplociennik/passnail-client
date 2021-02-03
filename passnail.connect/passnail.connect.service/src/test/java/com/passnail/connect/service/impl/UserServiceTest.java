package com.passnail.connect.service.impl;

import com.passnail.data.transfer.model.dto.LocalUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 23:14
 * Project: passnail-client
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Test
    public void testCreatingNewLocalUser() {
        LocalUserDto userDto = Mockito.mock(LocalUserDto.class);

        userService.createNewLocalUser(userDto);
    }
}