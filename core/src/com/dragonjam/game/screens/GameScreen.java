package com.dragonjam.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameworld.GameRenderer;
import com.dragonjam.game.gameworld.GameWorld;
import com.dragonjam.game.helpers.InputHandlers;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;

	// Track how long game is running.
	private float runTime = 0;


	public GameScreen() {
		Gdx.app.log("GameScreen", "Attached");

		// initialize world and renderer

		// Get the variables for the screen size.

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		Gdx.app.log("Screen Width: ", " "+ screenWidth);
		Gdx.app.log("Screen Height: ", " "+ screenHeight);
		// Flat setting for game height.
		float gameHeight = 360;

		// Scales the height of the game based on the size of the phone.
		float gameWidth = screenWidth / (screenHeight / gameHeight);
		Gdx.app.log("Game Width: ", " "+ gameWidth);
		Gdx.app.log("Game Height: ", " "+ gameHeight);
		// middle of the screen based on the height of the device/game window.
		int midPointX = (int) (gameWidth / 2);
		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(gameHeight, gameWidth, midPointX);

		// Add the inputhandler to the game.
		// we must past anything we want to be clickable to the input handler class.
		renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight, midPointX);
		InputHandlers handlers = new InputHandlers(world, renderer);
		Gdx.input.setInputProcessor(handlers);

		Boy boy = world.getBoy();
		Girl girl = world.getGirl();

		// TODO: 9/9/2017 Add mobs to method.
	}

	@Override
	public void render(float delta) {

		runTime += delta;
		world.update(delta);
		renderer.render(runTime);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
