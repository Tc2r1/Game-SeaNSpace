package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Mob extends Actor {

    Sprite sprite;

    public abstract void place(float worldWidth, float worldHeight);

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
