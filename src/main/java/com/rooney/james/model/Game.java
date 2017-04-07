package com.rooney.james.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="game_seq", allocationSize=100)
    private Long id;
    private String gameState = GameState.INIT.name();
    private String word;
    private String guess = " ";
    private int numRemainingIncorrectGuesses = 10;
    private String existingGuessedLetters;
    private boolean wordContainsGuessedLetter;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

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
        if (gameState.equals(GameState.INIT.getGameState())) {
            gameState = GameState.IN_PROGRESS.name();
        }

        if (word.equals(existingGuessedLetters)) {
            gameState = GameState.WON.name();
        } else if (numRemainingIncorrectGuesses == 0) {
            gameState = GameState.LOST.name();
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(id);
        hcb.append(word);
        hcb.append(player);

        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Game)) {
            return false;
        }

        Game that = (Game) obj;

        EqualsBuilder eb = new EqualsBuilder();
        eb.append(word, that.word);
        eb.append(player, that.player);

        return eb.isEquals();
    }
}