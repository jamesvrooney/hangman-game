package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import com.rooney.james.repository.GameRepository;
import com.rooney.james.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private GameRepository gameRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PlayerServiceImpl(PlayerRepository playerRepository,
                             GameRepository gameRepository,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /*@Override
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

    @Override
    public void save(Player user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setRoles(new HashSet<>(roleRepository.findAll()));
        playerRepository.save(user);
    }

    @Override
    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }*/


    @Override
    public void save(Player player) {
        player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));

        playerRepository.save(player);
    }

    @Override
    public Player findByUsername(String username) {
        return playerRepository.findByUsername(username);
    }


}
