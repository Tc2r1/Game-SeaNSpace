package com.dragonjam.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonjam.game.creatures.Boy;
import com.dragonjam.game.creatures.Girl;
import com.dragonjam.game.creatures.PlayerWhole;
import com.dragonjam.game.utility.Constants;
import com.dragonjam.game.utility.View;

public class PlayScreen implements Screen {
	
	private Stage stage;
		
	// ---- Viewing / camera objects ----
	OrthographicCamera cam;
	Viewport viewport;
	private Box2DDebugRenderer debugRenderer;
	
	// ---- Textures ----
	// This will be the bg of the game
	private Sprite bg;
	private Sprite boy;
	private Sprite girl;
	
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
		viewport = new StretchViewport(View.WIDTH.val(),
		                               View.HEIGHT.val(),
		                               cam);
		stage = new Stage(viewport, source);
		
		System.out.println("initializing textures...");
		bg = new Sprite(new Texture(Gdx.files.internal("images/background.png")));
		
		System.out.println("creating player...");
		boy = new Boy();
		girl = new Girl();
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
		
		// player.update(delta);
		
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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.getBatch().setProjectionMatrix(cam.combined);
		stage.getBatch().begin();
		
		// Draw back ground
		bg.draw(stage.getBatch());
		girl.draw(stage.getBatch());
		boy.draw(stage.getBatch());

		stage.getBatch().end();
		
	}

	/**
	 * Detects a window resize, updating the values in View, the viewport,
	 * and re-centering the camera.
	 *
	 * @param width         the new screenWidth
	 * @param height        the new screenHeight
	 *
	 * @author Cinders-P
	 */

	@Override
	public void resize(int width, int height) {
		View.RATIO.setVal((float) height / width);
		viewport.setWorldSize(View.WIDTH.val(), View.HEIGHT.val());
		viewport.update(width, height, true);
		updateSpritePositions();
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
		bg.getTexture().dispose();
	}

	/**
	 * Repositions the background in the center whenever the screen is resized.
	 * The background will always be set large enough to completely cover the
	 * viewport. If the width is relatively wide, moves the camera down
	 * 2 world units so that water is always visible.
	 *
	 * @author Cinders-P
	 */

	private void updateSpritePositions() {
		float imgRatio = 16f / 9f;
		float ratio = View.RATIO.val();
		float height = View.HEIGHT.val();
		float width = View.WIDTH.val();

		if (ratio > imgRatio) {
			bg.setSize(height * (1 / imgRatio), height);
		} else {
			bg.setSize(width, width * imgRatio);
		}
		bg.setCenter(width / 2, height / 2);

		if (ratio <= 4f / 3f) {
			cam.translate(0, -2);
		}
		boy.setPosition(width / 2 - 1.9f, height / 2 - 3.4f);
		girl.setPosition(width / 2 - 0.6f, height / 2 - 2.6f);
	}
}
