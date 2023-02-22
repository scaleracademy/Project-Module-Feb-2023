package com.scaler.blogapi.users;

import com.scaler.blogapi.security.jwt.JWTService;
import com.scaler.blogapi.users.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UsersServiceTests {
    @Autowired
    private UsersRepository usersRepository;
    private UsersService usersService;

    private UsersService getUsersService() {
        if (usersService == null) {
            var modelMapper = new ModelMapper();
            var passwordEncoder = new BCryptPasswordEncoder();
            var jwtService = new JWTService();
            usersService = new UsersService(
                    usersRepository,
                    modelMapper,
                    passwordEncoder,
                    jwtService
            );
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
