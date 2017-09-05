package com.dragonjam.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonjam.game.screens.PlayScreen;
import com.dragonjam.game.utility.InputHandler;
import com.dragonjam.game.utility.View;

public class GameMain extends Game {
	
	public SpriteBatch batch;

	@Override
	public void create() {
				
		batch = new SpriteBatch();

		// Will be used for displaying fonts at the correct size
		View.DENSITY.setVal(Gdx.graphics.getDensity());
		
		// Set up the input handler
		Gdx.input.setInputProcessor(new InputHandler());
		
		Gdx.graphics.setTitle("A Song of Sea and Space");
		this.setScreen(new PlayScreen(batch));
		
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
}
