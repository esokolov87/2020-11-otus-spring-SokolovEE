package ru.sokolovee.serviceadvertisement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sokolovee.serviceadvertisement.entities.Person;
import ru.sokolovee.serviceadvertisement.repositories.PersonRepository;

@Service
public class MyUserDetailsService {//implements UserDetailsService {

//    @Autowired
//    private PersonRepository personRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = personRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new MyUserPrincipal(user);
//    }
}
