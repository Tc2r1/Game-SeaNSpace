package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;

public class Boy extends Mob {

    public Boy() {
        sprite.setTexture(new Texture("images/sprites/boy.png"));
        resizeBounds();
    }

    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 1.9f, worldHeight / 2 - 3.4f);
    }
}
