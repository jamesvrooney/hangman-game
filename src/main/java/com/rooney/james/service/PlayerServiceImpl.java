package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import com.rooney.james.repository.GameRepository;
import com.rooney.james.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private GameRepository gameRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Player createNewPlayer() {
        Player player = new Player();

        player.setUsername("jamesvrooney");
        player.setPassword("password");

        playerRepository.save(player);

        return player;
    }

    @Override
    public Set<Game> getGamesForPlayer(Long playerId) {
        Player player = playerRepository.findOne(playerId);
        Set<Game> games = gameRepository.findByPlayer(player);

        return games;
    }
}
