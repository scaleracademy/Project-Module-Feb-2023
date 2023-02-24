package com.scaler.blogapi.users;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var savedUser = usersService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + savedUser.getId()))
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(
            @RequestBody LoginUserDTO loginUserDTO,
            @RequestParam(name = "token", defaultValue = "jwt") String token
    ) {
        // if token == "jwt" (default) generate JWT, if token == "auth_token" generate auth_token
        var authType = UsersService.AuthType.JWT;
        if (token.equals("auth_token")) {
            authType = UsersService.AuthType.AUTH_TOKEN;
        }
        var savedUser = usersService.loginUser(loginUserDTO, authType);
        return ResponseEntity.ok(savedUser);
    }

    @ExceptionHandler(UsersService.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UsersService.UserNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
