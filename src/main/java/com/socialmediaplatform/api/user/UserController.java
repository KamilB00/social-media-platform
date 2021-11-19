package com.socialmediaplatform.api.user;

import com.socialmediaplatform.api.user.dto.UserDTO;
import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import com.socialmediaplatform.api.user.dto.UserLoginDTO;
import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserService;
import com.socialmediaplatform.domain.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PostMapping("/signin")
    public String login(@RequestBody UserLoginDTO userLoginDTO){

        return userService.login(userLoginDTO);
    }

    @GetMapping("/me")
    public UserDetailsDTO getUserDetails(){
        return userService.getUserDetails();
    }

    // checking function to delete
    @GetMapping
    public List<UserDetailsDTO> getAllUsers(){
        return userService.getAllUsers();
    }

}
