package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;

import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public interface PlayerService {
    Player createNewPlayer();
    Set<Game> getGamesForPlayer(Long playerId);
}
