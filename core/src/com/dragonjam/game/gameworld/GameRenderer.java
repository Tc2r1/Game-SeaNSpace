package com.dragonjam.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Drowner;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.helpers.AssetLoader;


/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Renders Game Objects to Screen.
 */

public class GameRenderer {


	private GameWorld gameWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private static Sprite backgroundSprite;
	private SpriteBatch batcher;

	private int midPointX, gameHeight, gameWidth;

	// Game Objects

	private Boy boy;
	private Girl girl;
	private Drowner mob;

	// Game Assets
	private TextureRegion background;
	private Animation mobAnimation;
	private TextureRegion girlTR, boyTR;


	public GameRenderer(GameWorld gameWorld, int gameWidth, int gameHeight, int midPointX) {

		this.gameWorld = gameWorld;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.midPointX = midPointX;


		cam = new OrthographicCamera();
		cam.setToOrtho(false, 136, gameHeight);

		// Creates a sprit batcher to show animations,
		// assigns the cam object to display those animations.
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		backgroundSprite = new Sprite(AssetLoader.background);

		// Call Init Methods.
		initGameObjects();
		initAssets();

	}

	private void initAssets() {
		background = AssetLoader.background;
		boyTR = AssetLoader.boy;
		girlTR = AssetLoader.girl;
		mobAnimation = AssetLoader.monsterAnimation;
	}

	private void initGameObjects() {
		boy = gameWorld.getBoy();
		girl = gameWorld.getGirl();
		mob = gameWorld.getMob();

	}

	public void render(float runTime) {


		// Clear the background

		Gdx.gl.glClearColor(255, 255, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		// Begin SpriteBatcher
		batcher.begin();

		// Draw Background
		drawBackground();

		// Draw MainCharacters.
		batcher.draw(boyTR, boy.getX(), boy.getY(), boy.getWidth(), boy.getHeight());
		batcher.draw(girlTR, girl.getX(), girl.getY(), girl.getWidth(), girl.getHeight());

		// Draw monsters.
		batcher.draw((TextureRegion) mobAnimation.getKeyFrame(runTime), mob.getX(), mob.getY(), mob.getWidth(), mob.getHeight());
		batcher.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.CYAN);
		shapeRenderer.rect(boy.getCollisionBox().x, boy.getCollisionBox().y, boy.getCollisionBox().width, boy.getCollisionBox().height);

		shapeRenderer.setColor(Color.PINK);
		shapeRenderer.rect(girl.getCollisionBox().x, girl.getCollisionBox().y, girl.getCollisionBox().width, girl.getCollisionBox().height);

		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(mob.getCollisionBox().x, mob.getCollisionBox().y, mob.getCollisionBox().width, mob.getCollisionBox().height);


		shapeRenderer.end();
	}

	private void drawBackground() {
		batcher.disableBlending();
		batcher.draw(background, 0, 0, 136, gameHeight);
		batcher.enableBlending();

	}
}
