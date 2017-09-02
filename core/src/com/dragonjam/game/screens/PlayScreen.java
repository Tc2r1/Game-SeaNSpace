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
import com.dragonjam.game.creatures.PlayerWhole;
import com.dragonjam.game.utility.Constants;
import com.dragonjam.game.utility.InputHandler;

public class PlayScreen implements Screen {
	
	private Stage stage;
		
	// ---- Viewing / camera objects ----
	OrthographicCamera cam;
	Viewport viewport;
	
	// ---- Textures ----
	// This will be the background of the game
	private Texture background;
	
	// ---- Creatures ----
	PlayerWhole player;
	
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
		player = new PlayerWhole();
		
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
		
		// TODO: Use zoom??
//		if(InputHandler.scroll > 0) {
//			cam.zoom = cam.zoom + 0.05f;
//			InputHandler.scroll = 0;
//		} else if(InputHandler.scroll < 0) {
//			cam.zoom = cam.zoom - 0.05f;
//			InputHandler.scroll = 0;
//		}
		
		cam.update();
		
		player.update(delta);
		
		//System.out.println(InputHandler.lastClick);
		
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
		
		// Draw back ground
		stage.getBatch().draw(background, 0, 0, Constants.W_WIDTH, Constants.W_HEIGHT);
		
		// Draw player-controlled creatures
		// Attacker
//		stage.getBatch().draw(player.getAttackerTexture(), 
//				(Constants.W_WIDTH / 2) - (player.getAttackerTexture().getRegionWidth() * cam.zoom / 3 / 2) + player.getAttacker().getLocation().x, 
//				(Constants.W_HEIGHT / 2) - (player.getAttackerTexture().getRegionWidth() * cam.zoom / 2.5f / 2) - (Constants.W_HEIGHT * 0.05f), 
//				player.getAttackerTexture().getRegionWidth() * cam.zoom / 3, player.getAttackerTexture().getRegionHeight() * cam.zoom / 2.5f);
//		// Fisher
//		stage.getBatch().draw(player.getFisherTexture(), 
//				(Constants.W_WIDTH / 2) - (player.getFisherTexture().getRegionWidth() * cam.zoom / 3 / 2), 
//				(Constants.W_HEIGHT / 2) - (player.getFisherTexture().getRegionHeight() * cam.zoom / 2.5f / 2) - (Constants.W_HEIGHT * 0.08f), 
//				player.getAttackerTexture().getRegionWidth() * cam.zoom / 3, player.getAttackerTexture().getRegionHeight() * cam.zoom / 2.5f);
		// Draw the player components
		stage.getBatch().draw(player.getAttackerTexture(), 
				(Constants.W_WIDTH / 2) - (100 / 2) + player.getAttacker().getLocation().x, 
				(Constants.W_HEIGHT / 2) - (200 / 2) - (Constants.W_HEIGHT * 0.05f), 
				100, 200);
		// Fisher
		stage.getBatch().draw(player.getFisherTexture(), 
				(Constants.W_WIDTH / 2) - (100 / 2), 
				(Constants.W_HEIGHT / 2) - (200 / 2) - (Constants.W_HEIGHT * 0.08f), 
				100, 200);
		
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
