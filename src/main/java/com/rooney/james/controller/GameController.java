package com.rooney.james.controller;

import com.rooney.james.model.Game;
import com.rooney.james.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @PostMapping("/hangman/game/create")
    @ResponseBody
    public Game createNewGame() {
        Game game = gameService.createNewGame();

        return game;
    }
    @PostMapping("/game/{gameId}/{guessedLetter}")
    @ResponseBody
    public Game makeGuess(@PathVariable Long gameId, @PathVariable char guessedLetter) {

        return gameService.checkGuess(gameId, guessedLetter);
    }
}
