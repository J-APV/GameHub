package com.example.application.services;

import com.example.application.data.GameRepository;
import com.example.application.data.IndieGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/indie-games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/{title}")
    public IndieGame getGameByTitle(@PathVariable String title) {
        return gameRepository.findByTitle(title);
    }

    @GetMapping("/all")
    public List<IndieGame> getAllGames() {
        return gameRepository.findAll();
    }
}
