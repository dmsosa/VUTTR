package com.duvi.vuttr.controller;


import com.duvi.vuttr.controller.exception.UserAlreadyExistsException;
import com.duvi.vuttr.controller.exception.UserNotFoundException;
import com.duvi.vuttr.domain.user.*;
import com.duvi.vuttr.infra.security.TokenService;
import com.duvi.vuttr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    ResponseEntity loginUser(@RequestBody AuthDTO data) throws UserNotFoundException {
        if (userRepository.findByLogin(data.login()) == null ){
            throw new UserNotFoundException(data.login());
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return new ResponseEntity<>(new LoginDTO(token, (User) auth.getPrincipal()), HttpStatus.OK);
    }

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@RequestBody RegisterDTO data) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new UserAlreadyExistsException(data.login());
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data, encryptedPassword);
        userRepository.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Creation:","Successful");
        return new ResponseEntity<>(newUser, headers, HttpStatus.CREATED);
    }
}
