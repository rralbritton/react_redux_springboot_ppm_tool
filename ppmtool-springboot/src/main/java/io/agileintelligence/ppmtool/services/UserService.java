package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.security.PpmUser;
import io.agileintelligence.ppmtool.repositories.PpmUserRepository;
import io.agileintelligence.ppmtool.services.exceptionServices.UsernameAlreadyExistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PpmUserRepository ppmUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //built into spring io.agileintelligence.ppmtool.security

    /*Register a new user*/
    public PpmUser saveUser (PpmUser newUser){

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username must be unique
            newUser.setUsername(newUser.getUsername().toLowerCase());
            newUser.setFirstName(newUser.getFirstName().toUpperCase());
            newUser.setLastName(newUser.getLastName().toUpperCase());

            //Do NOT persist or show the confirm Password
            newUser.setConfirmPassword("");
            return ppmUserRepository.save(newUser);
        } catch (Exception e){
            throw new UsernameAlreadyExistsService("Username: '" + newUser.getUsername() + "' already exists");
        }
    }

}
