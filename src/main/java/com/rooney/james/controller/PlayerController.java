package com.rooney.james.controller;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import com.rooney.james.service.GameService;
import com.rooney.james.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Controller
public class PlayerController {

    private PlayerService playerService;
    private GameService gameService;

    @Autowired
    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    /*@GetMapping("/")
    public String index(ModelMap modelMap) {
        Game game = gameService.createNewGame();

        modelMap.addAttribute("game", game);

        return "index";
    }*/

    @PostMapping("/hangman/player/new")
    @ResponseBody
    public Player createNewPlayer() {
        Player player = playerService.createNewPlayer();

        return player;
    }

    @GetMapping("/hangman/player/{playerId}/games")
    @ResponseBody
    public Set<Game> getPlayerGames(@PathVariable Long playerId) {
        Set<Game> gamesForPlayer = gameService.getGamesForPlayer(playerId);

        return gamesForPlayer;
    }

    /*@PostMapping("/game/{gameId}/{guessedLetter}")
    @ResponseBody
    public Game makeGuess(@PathVariable Long gameId, @PathVariable char guessedLetter) {

        return gameService.checkGuess(gameId, guessedLetter);
    }*/
}
