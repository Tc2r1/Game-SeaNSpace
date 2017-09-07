package com.dragonjam.game.controllers;

import java.util.Random;

public class DrownerController {

    private Random rand;

    public DrownerController() {
        rand = GameManager.getInstance().rand();
    }
}
