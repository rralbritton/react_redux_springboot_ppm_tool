package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.models.security.PpmUser;
import io.agileintelligence.ppmtool.repositories.PpmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    PpmUserRepository ppmUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PpmUser ppmUser = ppmUserRepository.findByUsername(username);
        if(ppmUser == null) new UsernameNotFoundException("User not found");
        return ppmUser;
    }

    @Transactional //Make sure to grab the spring one
    public PpmUser loadUserById(Long id){
        PpmUser ppmUser = ppmUserRepository.getById(id);
        if(ppmUser == null) new UsernameNotFoundException("User not found");
        return ppmUser;
    }
}
