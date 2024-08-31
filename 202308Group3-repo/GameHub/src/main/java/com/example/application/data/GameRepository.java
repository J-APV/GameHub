package com.example.application.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameRepository extends JpaRepository<IndieGame, Long>, JpaSpecificationExecutor<IndieGame> {

    IndieGame findByTitle(String title);
    List<IndieGame> findAll();

}
