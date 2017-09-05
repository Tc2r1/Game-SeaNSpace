package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Boy extends Sprite {

    public Boy() {
        super(new Texture("images/sprites/boy.png"));
        setSize(2.5f, 2.5f * (5f/3f));
    }
}
