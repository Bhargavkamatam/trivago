package com.trivago.service;

import com.trivago.entity.AppUser;
import com.trivago.payload.LoginDto;
import com.trivago.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public static AppUser createUser(AppUser user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        AppUser createUser = appUserRepository.save(user);
        return createUser;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUserName = appUserRepository.findByUsername(loginDto.getUsername());
        if (opUserName.isPresent()) {
            AppUser appUser = opUserName.get();
            String token = null;
            if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {

                return jwtService.generateToken(appUser);
            }
        }
            return null; // Placeholder, actual verification logic should be added here
        }
    }