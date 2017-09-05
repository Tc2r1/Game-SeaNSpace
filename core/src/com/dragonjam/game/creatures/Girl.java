package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Girl extends Sprite {
    public Girl() {
        super(new Texture("images/sprites/girl.png"));
        setSize(2.5f, 2.5f * (5f / 3f));
    }
}
