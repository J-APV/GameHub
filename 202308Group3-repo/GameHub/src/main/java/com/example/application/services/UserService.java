package com.example.application.services;

import com.example.application.data.User;
import com.example.application.data.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import com.example.application.security.SecurityConfiguration;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String name, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



        User user = new User();
        user.setUsername(username);


            user.setUsername(username);
            user.setName(name);
            String hashedPassword = passwordEncoder.encode(password);
            user.setHashedPassword(hashedPassword);
            return userRepository.save(user);

        }







}
