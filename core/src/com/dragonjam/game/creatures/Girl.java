package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Girl extends Mob {

    public Girl() {
        sprite = new Sprite(new Texture("images/sprites/girl.png"));
        sprite.setSize(2.5f, 2.5f * (5f / 3f));
    }

    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 0.8f, worldHeight / 2 - 2.6f);
    }

}
