package com.rooney.james.controller;

import com.rooney.james.model.Game;
import com.rooney.james.model.Word;
import com.rooney.james.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String index() {
        return "index";
    }

    @PostMapping("/game/{gameId}/{guessedLetter}")
    @ResponseBody
    public Game makeGuess(@PathVariable int gameId, @PathVariable char guessedLetter) {


        return gameService.checkGuess(gameId, guessedLetter);
    }
}
