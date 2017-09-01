package com.dragonjam.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonjam.game.creatures.Player;
import com.dragonjam.game.utility.Constants;

public class PlayScreen implements Screen {
	
	private Stage stage;
		
	// ---- Viewing / camera objects ----
	OrthographicCamera cam;
	Viewport viewport;
	
	// ---- Textures ----
	// This will be the background of the game
	private Texture background;
	
	// ---- Creatures ----
	Player player;
	
	/**
	 * libGDX object for the main play aspect
	 * of the game. This screen will handle
	 * rendering and updating / the game loop.
	 * 
	 * @param source
	 * the SpriteBatch that will be used for drawing
	 * 
	 * @author Rane
	 */
	public PlayScreen(SpriteBatch source) {
				
		System.out.println("setting up render components...");
		cam = new OrthographicCamera();
		viewport = new ScreenViewport(cam);
		stage = new Stage(viewport, source);
		
		System.out.println("initializing textures...");
		background = new Texture(Gdx.files.internal("images/background/background.png"));
		
		System.out.println("creating player...");
		player = new Player();
		
	}
	
	/**
	 * Method that will be used to update different
	 * game objects and elements, such as score, 
	 * time, and game end.
	 * 
	 * @param delta
	 * the delta time (amount of time since last frame)
	 * 
	 * @author Rane
	 */
	private void update(float delta) {
		
		cam.update();
		
		player.update(delta);
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		// First, update the game logic
		update(delta);
		
		// Then render
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.getBatch().setProjectionMatrix(cam.combined);
		stage.getBatch().begin();
		
		stage.getBatch().draw(background, 0, 0, Constants.W_WIDTH, Constants.W_HEIGHT);
		stage.getBatch().draw(player.getAnimation().getKeyFrame(player.getTime(), true), 
				(Constants.W_WIDTH / 2) - (player.getF_WIDTH() / 2), (Constants.W_HEIGHT / 2) - (player.getF_HEIGHT() / 2));
		
		stage.getBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {
		
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
