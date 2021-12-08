package com.socialmediaplatform.api.user;

import com.socialmediaplatform.api.user.dto.UserDTO;
import com.socialmediaplatform.api.user.dto.UserDetailsDTO;
import com.socialmediaplatform.api.user.dto.UserLoginDTO;
import com.socialmediaplatform.domain.user.User;
import com.socialmediaplatform.domain.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
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
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public UserDetailsDTO getUserDetails(){
        return userService.getUserDetails();
    }

    @GetMapping("/{username}")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public UserDetailsDTO getUserDetails (@PathVariable String username){
       return userService.getUserDetails(() -> username);
    }

    @PostMapping("/{username}/follow")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void follow(@PathVariable String username){
        userService.follow(() -> username);
    }

    @DeleteMapping("/{username}/unfollow")
    @Operation(summary = "user details", security = @SecurityRequirement(name = "bearer"))
    public void unfollow(@PathVariable String username){
        //TODO2
    }

}
