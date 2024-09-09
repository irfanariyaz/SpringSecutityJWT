package com.example.Security.Controller;

import com.example.Security.model.Users;
import com.example.Security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users  user) {
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody  Users user){
        return userService.verify(user);
    }

    // Other user-related endpoints
}
