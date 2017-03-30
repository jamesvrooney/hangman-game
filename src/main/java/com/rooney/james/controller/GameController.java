package com.rooney.james.controller;

import com.rooney.james.model.Game;
import com.rooney.james.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String index(ModelMap modelMap) {
        Game game = gameService.createNewGame();

        modelMap.addAttribute("game", game);

        return "index";
    }

    @PostMapping("/hangman/game/new")
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
