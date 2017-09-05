package com.dragonjam.game.controllers;

public class GameManager {

    private static GameManager manager = new GameManager();

    private GameManager() {}

    public GameManager getInstance() {
        return manager;
    }
}
