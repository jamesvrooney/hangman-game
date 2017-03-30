package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.Player;
import com.rooney.james.repository.GameRepository;
import com.rooney.james.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private PlayerRepository playerRepository;
    private List<String> words = getWordsList();
    private Random randomGen = new Random(System.nanoTime());

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    private List<String> getWordsList() {
        List<String> words = new ArrayList<>();

        words.add("elephant");
        words.add("hippopotamus");
        words.add("flamingo");
        words.add("gorilla");
        words.add("chimpanzee");
        words.add("alligator");
        words.add("giraffe");

        return words;
    }

    @Override
    @Transactional
    public Game createNewGame() {
        Game game = new Game(getRandomWord());

//        gameRepository.save(game);

        Long playerId = 1L;
        Player player = playerRepository.findOne(playerId);

        game.setPlayer(player);

        gameRepository.save(game);

        player.getGames().add(game);

//        playerRepository.save(player);

        return game;
    }

    @Override
    @Transactional
    public Game checkGuess(Long gameId, char guessedLetter) {
        Game game = gameRepository.findOne(gameId);

        game.checkGuess(guessedLetter);

        return game;
    }

    private String getRandomWord() {
        return words.get(randomGen.nextInt(words.size()));
    }

    @Override
    public Set<Game> getGamesForPlayer(Long playerId) {
        Player player = playerRepository.findOne(playerId);
        Set<Game> games = gameRepository.findByPlayer(player);
//        Set<Game> games = null;

        return games;
    }
}
