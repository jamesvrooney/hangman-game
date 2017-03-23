package com.rooney.james.model;

/**
 * Created by jamesvrooney on 22/03/17.
 */
public enum GameState {
    WON("WON"),
    LOST("LOST"),
    IN_PROGRESS("IN_PROGRESS");

    private String gameState;

    GameState(String gameState) {
        this.gameState = gameState;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}
