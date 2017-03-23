package com.rooney.james.service;

import com.rooney.james.model.Game;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public interface GameService {
    Game createNewGame();

    Game checkGuess(int gameId, char guessedLetter);
}
