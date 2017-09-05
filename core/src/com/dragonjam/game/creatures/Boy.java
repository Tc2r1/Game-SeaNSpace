package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Boy extends Mob {

    public Boy() {
        sprite = new Sprite(new Texture("images/sprites/boy.png"));
        sprite.setSize(2.5f, 2.5f * (5f/3f));

    }

    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 1.9f, worldHeight / 2 - 3.4f);
    }
}
