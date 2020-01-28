package io.agileintelligence.ppmtool.controllers;

import io.agileintelligence.ppmtool.models.security.PpmUser;
import io.agileintelligence.ppmtool.payload.JwtLoginSuccessResponse;
import io.agileintelligence.ppmtool.payload.LoginRequest;
import io.agileintelligence.ppmtool.security.JwtTokenProvider;
import io.agileintelligence.ppmtool.services.UserService;
import io.agileintelligence.ppmtool.services.ValidationErrorService;
import io.agileintelligence.ppmtool.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static io.agileintelligence.ppmtool.security.SecurityConstant.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ValidationErrorService validationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser (@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if(errorMap != null)return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        String userToken = jwt.split("Bearer ")[1];

        return ResponseEntity.ok(new JwtLoginSuccessResponse(true, userToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@Valid @RequestBody PpmUser ppmUser, BindingResult result){
        //Validate that passwords match
        userValidator.validate(ppmUser, result);
        
        ResponseEntity<?> errorMap = validationErrorService.ValidationErrorService(result);
        if(errorMap != null) return errorMap;

        PpmUser newUser = userService.saveUser(ppmUser);
        return new ResponseEntity<PpmUser>(newUser, HttpStatus.CREATED);
    }
}

