package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Mob extends Actor {

    Sprite sprite;

    public abstract void place(float worldWidth, float worldHeight);

    Mob() {
        sprite = new Sprite();
        sprite.setSize(2.5f, 2.5f * (5f / 3f));
    }

    void resizeBounds() {
        sprite.setRegion(0, 0, sprite.getTexture().getWidth(), sprite.getTexture().getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
