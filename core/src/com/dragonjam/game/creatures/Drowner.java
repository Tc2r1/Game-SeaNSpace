package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dragonjam.game.controllers.GameManager;
import com.dragonjam.game.utility.View;

public class Drowner extends Mob {

    private Animation walkAnimation;
    private float runningTime;
    private float speed;

    /**
     * True if the drowner should spawn to the right of the camera and walk left.
     */
    private boolean right;
    private boolean stopped;

    public Drowner(boolean rightSide) {
        sprite.setTexture(new Texture("images/sprites/eating.png"));
        resizeBounds();
        speed = 0.4f + GameManager.getInstance().rand().nextInt(21) / 100f;

        if (rightSide) {
            right = true;
            speed = -speed;
            sprite.flip(true, false);
            sprite.setPosition(View.WIDTH.val(), View.HEIGHT.val() / 2 - 2.5f);
        } else {
            sprite.setPosition(-sprite.getWidth(), View.HEIGHT.val() / 2 - 2.5f);
        }

        walkAnimation = new Animation(
                1f / 5f,
                new TextureAtlas("images/sprites/drowner.atlas").getRegions()
        );
    }

    public void updatePosition() {
        if (stopped) return;

        float stopPos = View.WIDTH.val() / 2  + (right ? -0.8f : -3.1f);
        if ((!right && sprite.getX() > stopPos) || (right && (sprite.getX() < stopPos)))
            stopped = true;
        else
            sprite.translateX(speed * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (stopped) {
            super.draw(batch, parentAlpha);
        } else {
            runningTime += Gdx.graphics.getDeltaTime();
            TextureRegion next = (TextureRegion) walkAnimation
                    .getKeyFrame(runningTime, true);
            if (right) {
                batch.draw(next, sprite.getX() + sprite.getWidth(), sprite.getY(), -sprite
                        .getWidth(), sprite.getHeight());
            } else {
                batch.draw(next, sprite.getX(), sprite.getY(), sprite
                        .getWidth(), sprite.getHeight());
            }
        }
    }

    @Override
    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(sprite.getX(), worldHeight / 2 - 2.5f);
    }
}
