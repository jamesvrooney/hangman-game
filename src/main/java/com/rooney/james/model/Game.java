package com.rooney.james.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Entity
@Data @NoArgsConstructor
public class Game {
    @Id @GeneratedValue
    private int id;
    private GameState gameState = GameState.IN_PROGRESS;
    private String word;
    private char guess;
    private int numRemainingGuesses = 10;
    private String existingGuessedLetters;

    public Game(String randomWord) {
        this.word = randomWord;
        this.existingGuessedLetters = word.replaceAll(".", "_");
        this.numRemainingGuesses = 10;
    }

    public void checkGuess(char guessedLetter) {
        String guessedLetterAsString = Character.toString(guessedLetter);

        if (word.contains(guessedLetterAsString)) {
            updateExistingGuessedLetters(guessedLetter);
        } else {
            numRemainingGuesses--;
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
            gameState = GameState.WON;
        } else if (numRemainingGuesses == 0) {
            gameState = GameState.LOST;
        }
    }
}