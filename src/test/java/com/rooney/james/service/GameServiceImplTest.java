package com.rooney.james.service;

import com.rooney.james.model.Game;
import com.rooney.james.model.GameState;
import com.rooney.james.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public class GameServiceImplTest {

    private GameService gameService;

//    @Mock
//    private GameRepository gameRepository;
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

    }

    @Test
    public void checkGuessWordDoesNotContainGuessedLetter() throws Exception {
        // Given
        int gameId = 23;
        Game game = new Game();
        game.setId(gameId);

        String elephant = "elephant";

        game.setWord(elephant);

//        GameRepository gameRepository = mock(GameRepository.class);

        given(gameRepository.findOne(gameId)).willReturn(game);

        // when
        char guessedLetter = 'w';
        game = gameService.checkGuess(gameId, guessedLetter);

        // then
        int expectedNumOfGuessesRemaining = 9;
        int actualNumRemainingGuesses = game.getNumRemainingGuesses();

        assertEquals(expectedNumOfGuessesRemaining, actualNumRemainingGuesses);
    }
}