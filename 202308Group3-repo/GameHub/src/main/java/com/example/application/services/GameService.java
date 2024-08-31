package com.example.application.services;

import com.example.application.data.GameRepository;
import com.example.application.data.IndieGame;
import java.util.Optional;

import com.example.application.data.User;
import com.example.application.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository repository;


    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Optional<IndieGame> get(Long id) {
        return repository.findById(id);
    }

    public IndieGame update(IndieGame entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<IndieGame> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<IndieGame> list(Pageable pageable, Specification<IndieGame> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }
    @Autowired
    private GameRepository gameRepository;
    public void createGame(String title , String description, String username, double price) {
        IndieGame newGame = new IndieGame();
        newGame.setTitle(title);
        newGame.setDescription(description);
        newGame.setUsername(username);
        newGame.setPrice(price);
        repository.save(newGame);


    }




}
