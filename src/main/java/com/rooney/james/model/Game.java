package com.rooney.james.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Entity
@Table(name = "game")
@Data @NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="game_seq", allocationSize=100)
    private Long id;
    private String gameState = GameState.IN_PROGRESS.name();
    private String word;
    private char guess = ' ';
    private int numRemainingIncorrectGuesses = 10;
    private String existingGuessedLetters;
    private boolean wordContainsGuessedLetter;

    public Game(String randomWord) {
        this.word = randomWord;
        this.existingGuessedLetters = word.replaceAll(".", "_");
        this.numRemainingIncorrectGuesses = 10;
    }

    public void checkGuess(char guessedLetter) {
        wordContainsGuessedLetter = false;
        String guessedLetterAsString = Character.toString(guessedLetter);

        if (word.contains(guessedLetterAsString)) {
            updateExistingGuessedLetters(guessedLetter);
            wordContainsGuessedLetter = true;
        } else {
            numRemainingIncorrectGuesses--;
            wordContainsGuessedLetter = false;
        }

        checkState();
    }

    private void updateExistingGuessedLetters(char guessedLetter) {
        StringBuffer sb = new StringBuffer(existingGuessedLetters);
        int indexOfGuessedLetterInWord = word.indexOf(guessedLetter);

        while (indexOfGuessedLetterInWord >= 0) {
            sb.setCharAt(indexOfGuessedLetterInWord, guessedLetter);
            indexOfGuessedLetterInWord = word.indexOf(guessedLetter, indexOfGuessedLetterInWord + 1);
        }

        existingGuessedLetters = sb.toString();
    }

    private void checkState() {
        if (word.equals(existingGuessedLetters)) {
            gameState = GameState.WON.name();
        } else if (numRemainingIncorrectGuesses == 0) {
            gameState = GameState.LOST.name();
        }
    }
}