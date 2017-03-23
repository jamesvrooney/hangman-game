package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.GameState;
import com.rooney.james.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public class GameServiceImplTest {

    private GameService gameService;
    GameRepository gameRepository = mock(GameRepository.class);

    @Before
    public void setup() {
        gameService = new GameServiceImpl(gameRepository);
    }

    @Test
    public void createNewGame() throws Exception {
        Game game = gameService.createNewGame();

        assertThat(game.getGameState(), is(GameState.IN_PROGRESS));
    }

    @Test
    public void checkGuessWordContainsGuessedLetter() throws Exception {
        // Given
        int gameId = 23;
        String elephant = "elephant";

        Game game = new Game(elephant);
        game.setId(gameId);

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'e';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        int expectedNumOfIncorrectGuessesRemaining = 10;
        int actualNumRemainingIncorrectGuesses = game.getNumRemainingIncorrectGuesses();

        assertEquals(expectedNumOfIncorrectGuessesRemaining, actualNumRemainingIncorrectGuesses);
        assertTrue(game.isWordContainsGuessedLetter());
        assertThat(game.getGameState(), is(GameState.IN_PROGRESS));
    }

    @Test
    public void checkStatusWhenGameIsWon() throws Exception {
        // Given
        int gameId = 23;
        String elephant = "elephant";

        Game game = new Game(elephant);
        game.setId(gameId);
        game.setExistingGuessedLetters("_l_phant");

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'e';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        String expectedGuessedLetters = "elephant";

        assertEquals(expectedGuessedLetters, game.getExistingGuessedLetters());
        assertTrue(game.isWordContainsGuessedLetter());
        assertThat(game.getGameState(), is(GameState.WON));
    }

    @Test
    public void checkGuessedLettersUpdatedWhenLetterIsPresent() throws Exception {
        // Given
        int gameId = 23;
        String elephant = "elephant";

        Game game = new Game(elephant);
        game.setId(gameId);

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'e';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        String expectedGuessedLetters = "e_e_____";

        assertEquals(expectedGuessedLetters, game.getExistingGuessedLetters());
        assertTrue(game.isWordContainsGuessedLetter());
        assertThat(game.getGameState(), is(GameState.IN_PROGRESS));
    }

    @Test
    public void checkGuessWordDoesNotContainGuessedLetter() throws Exception {
        // Given
        int gameId = 23;
        String elephant = "elephant";

        Game game = new Game(elephant);
        game.setId(gameId);

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'w';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        int expectedNumOfIncorrectGuessesRemaining = 9;
        int actualNumRemainingIncorrectGuesses = game.getNumRemainingIncorrectGuesses();

        assertEquals(expectedNumOfIncorrectGuessesRemaining, actualNumRemainingIncorrectGuesses);
        assertThat(game.getGameState(), is(GameState.IN_PROGRESS));
        assertFalse(game.isWordContainsGuessedLetter());
    }

    @Test
    public void checkGuessWordStateChangesToLost() throws Exception {
        // Given
        int gameId = 23;
        String elephant = "elephant";

        Game game = new Game(elephant);
        game.setId(gameId);
        game.setExistingGuessedLetters("ele__ant");
        game.setNumRemainingIncorrectGuesses(1);

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'w';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        int expectedNumOfIncorrectGuessesRemaining = 0;
        int actualNumRemainingIncorrectGuesses = game.getNumRemainingIncorrectGuesses();

        assertEquals(expectedNumOfIncorrectGuessesRemaining, actualNumRemainingIncorrectGuesses);
        assertFalse(game.isWordContainsGuessedLetter());
        assertThat(game.getGameState(), is(GameState.LOST));
    }
}