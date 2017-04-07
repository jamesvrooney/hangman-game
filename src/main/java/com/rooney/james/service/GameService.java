package com.rooney.james.service;

import com.rooney.james.model.Game;

import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public interface GameService {
    Game createNewGame();

    Game checkGuess(Long gameId, char guessedLetter);

//    Set<Game> getGamesForPlayer(Long playerId);
}
