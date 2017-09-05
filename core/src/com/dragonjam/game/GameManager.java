package com.dragonjam.game;

public class GameManager {

    private GameManager manager = new GameManager();

    private GameManager() {}

    public GameManager getInstance() {
        return manager;
    }
}
