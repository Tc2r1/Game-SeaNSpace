package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Drowner extends Mob {

    private Animation walkAnimation;
    private float runningTime;
    private boolean right;

    public Drowner() {
        sprite.setTexture(new Texture("images/sprites/eating.png"));
        resizeBounds();
        walkAnimation = new Animation(
                1f / 5f,
                new TextureAtlas("images/sprites/drowner.atlas").getRegions()
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        runningTime += Gdx.graphics.getDeltaTime();
        TextureRegion next = (TextureRegion) walkAnimation.getKeyFrame(runningTime, true);
        batch.draw(next, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        // super.draw(batch, parentAlpha);
    }

    @Override
    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 0.8f, worldHeight / 2 - 2.5f);
    }
}
