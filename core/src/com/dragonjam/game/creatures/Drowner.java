package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Drowner extends Mob {

    private static Animation walkAnimation;

    public Drowner() {
        sprite.setTexture(new Texture("images/sprites/eating.png"));
    }

    @Override
    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 0.8f, worldHeight / 2 - 2.6f);
    }
}
