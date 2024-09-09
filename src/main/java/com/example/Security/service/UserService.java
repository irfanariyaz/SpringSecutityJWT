package com.example.Security.service;

import com.example.Security.model.Users;
import com.example.Security.repo.UserRepo;
import com.example.Security.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private  final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private  final JWTService jwtService;

    //register

    public  Users register (Users user){
        user.setPassword(encoder.encode(user.getPassword()));
          return   userRepo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(user.getUsername());
                System.out.println("generatede token:"+ token);
                return token;
            }

        return "failed";
    }
}
