package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsersServiceTests {
    @Autowired
    private UsersRepository usersRepository;
    private UsersService usersService;

    private UsersService getUsersService() {
        if (usersService == null) {
            var modelMapper = new ModelMapper();
            usersService = new UsersService(usersRepository, modelMapper);
        }
        return usersService;
    }


    @Test
    public void testCreateUser() {
        var newUserDTO = new CreateUserDTO();
        newUserDTO.setEmail("arnav@email.com");
        newUserDTO.setPassword("password");
        newUserDTO.setUsername("arnav123");
        var savedUser = getUsersService().createUser(newUserDTO);
        assertNotNull(savedUser);
    }
}
