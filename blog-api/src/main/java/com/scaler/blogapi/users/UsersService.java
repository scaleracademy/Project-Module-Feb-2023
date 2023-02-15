package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersService(
            @Autowired UsersRepository usersRepository,
            @Autowired ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        // TODO: Encrypt password
        // TODO: Validate email
        // TODO: Check if username already exists
        var newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        var savedUser = usersRepository.save(newUserEntity);
        var userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);

        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
        var userEntity = usersRepository.findByUsername(loginUserDTO.getUsername());
        if (userEntity == null) {
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        // TODO: Encrypt password
        if (!userEntity.getPassword().equals(loginUserDTO.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        var userResponseDTO = modelMapper.map(userEntity, UserResponseDTO.class);

        return userResponseDTO;
    }


    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(Integer id) {
            super("User with id: " + id + " not found");
        }

        public UserNotFoundException(String username) {
            super("User with username: " + username + " not found");
        }
    }

    public static class IncorrectPasswordException extends IllegalArgumentException {
        public IncorrectPasswordException() {
            super("Incorrect password");
        }
    }



}
