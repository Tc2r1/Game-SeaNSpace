package com.dragonjam.game.controllers;

import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.creatures.Drowner;

import java.util.Random;

public class DrownerController {

    private Random rand;
    private Array<Drowner> drowners;
    public DrownerController() {
        rand = GameManager.getInstance().rand();
        drowners = new Array<Drowner>();
    }

    public Drowner newDrowner() {
        Drowner d = new Drowner(rand.nextBoolean());
        drowners.add(d);
        return d;
    }

    public void updateDrowners() {
        for (Drowner d : drowners) {
            d.updatePosition();
        }
    }
}
