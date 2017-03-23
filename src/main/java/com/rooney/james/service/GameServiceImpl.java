package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private List<String> words = getWordsList();
    private Random randomGen = new Random(System.nanoTime());

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    private List<String> getWordsList() {
        List<String> words = new ArrayList<>();

        words.add("elephant");
        words.add("hippopotamus");
        words.add("flamingo");
        words.add("gorilla");
        words.add("chimpanzee");
        words.add("alligator");

        return words;
    }

    @Override
    public Game createNewGame() {
        Game game = new Game(getRandomWord());

        gameRepository.save(game);

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
}
