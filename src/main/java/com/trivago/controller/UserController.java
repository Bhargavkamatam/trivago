package com.trivago.controller;


import com.trivago.entity.AppUser;
import com.trivago.payload.JWTTokenDto;
import com.trivago.payload.LoginDto;
import com.trivago.repository.AppUserRepository;
import com.trivago.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class  UserController {

    private AppUserRepository appUserRepository;
     private UserService userService;
         public UserController(AppUserRepository appUserRepository,UserService userService) {
        this.appUserRepository = appUserRepository;
             this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody AppUser user
    )    {
             if(appUserRepository.existsByEmail(user.getEmail())){
             return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        if(appUserRepository.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Username  already exists", HttpStatus.BAD_REQUEST);
        }
        AppUser createdUser= UserService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }//4/6/24
    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin( @RequestBody LoginDto loginDto){
       String  token = userService.verifyLogin(loginDto);
       if (token != null) {
           JWTTokenDto jWtToken = new JWTTokenDto();
           jWtToken.setType("JWT Token");
           jWtToken.setToken(token);
           return new ResponseEntity<>(jWtToken,HttpStatus.OK);
       }else {
           return new ResponseEntity<>("Invalid token",HttpStatus.OK);
       }
    }
}
