package com.dragonjam.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Drowner;
import com.dragonjam.game.gameobjects.Girl;
import com.dragonjam.game.gameobjects.Monster;
import com.dragonjam.game.gameobjects.MonsterHandler;
import com.dragonjam.game.helpers.AssetLoader;


/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Renders Game Objects to Screen.
 */

public class GameRenderer {


	private GameWorld gameWorld;
	private OrthographicCamera cam;
	private Vector3 touchPos;
	private ShapeRenderer shapeRenderer;
	private static Sprite backgroundboy;
	private SpriteBatch batcher;

	private int midPointX, gameHeight, gameWidth;

	// Game Objects

	private Boy boy;
	private Girl girl;
	private MonsterHandler monsterHandler;
	private Drowner mob;
	private Array<Monster> gameObjects;

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
		Gdx.app.log("gameHeight: ", gameHeight+"");

		// Creates a sprit batcher to show animations,
		// assigns the cam object to display those animations.
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		backgroundboy = new Sprite(AssetLoader.background);

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
		monsterHandler = gameWorld.getMonsterHandler();
		mob = monsterHandler.getDrowner();
		touchPos = new Vector3();

		gameObjects = new Array<Monster>();
		gameObjects.add(mob);

	}

	public void render(float runTime) {


		// Clear the background

		Gdx.gl.glClearColor(255, 255, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		// Begin boyBatcher
		batcher.begin();

		// Draw Background
		drawBackground();

		// Draw MainCharacters.
		batcher.draw(boyTR, boy.getX(), boy.getY(), boy.getWidth(), boy.getHeight());
		batcher.draw(girlTR, girl.getX(), girl.getY(), girl.getWidth(), girl.getHeight());

		// Draw monsters.
		batcher.draw((TextureRegion) mobAnimation.getKeyFrame(runTime), mob.getX(), mob.getY(), mob.getWidth(), mob.getHeight());
		batcher.end();

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
//		shapeRenderer.rect(boy.getCollisionBox().x, boy.getCollisionBox().y, boy.getCollisionBox().width, boy.getCollisionBox().height);
//
//		shapeRenderer.setColor(Color.PINK);
//		shapeRenderer.rect(girl.getCollisionBox().x, girl.getCollisionBox().y, girl.getCollisionBox().width, girl.getCollisionBox().height);
//
//		shapeRenderer.setColor(Color.GREEN);
//		shapeRenderer.rect(mob.getCollisionBox().x, mob.getCollisionBox().y, mob.getCollisionBox().width, mob.getCollisionBox().height);
//
//
//		shapeRenderer.end();


		cam.unproject(touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		if (Gdx.input.justTouched()) {
			// if boy clicked
			if (touchPos.x > boy.getX() && touchPos.x < boy.getX() + boy.getWidth()) {
				if (touchPos.y > boy.getY() && touchPos.y < boy.getY() + boy.getHeight()) {
					Gdx.app.log("Clicked: ", "boy");
					boy.onClick();

				}
			}

			// if girl clicked
			if (touchPos.x > girl.getX() && touchPos.x < girl.getX() + girl.getWidth()) {
				if (touchPos.y > girl.getY() && touchPos.y < girl.getY() + girl.getHeight()) {
					Gdx.app.log("Clicked: ", "girl");
					girl.onClick();

				}
			}


			for (Monster npc : gameObjects) {
				if (touchPos.x > npc.getCollisionBox().getX() && touchPos.x < npc.getCollisionBox().getX() + npc.getCollisionBox().getWidth()) {
					if (touchPos.y > npc.getCollisionBox().getY() && touchPos.y < npc.getCollisionBox().getY() + npc.getCollisionBox().getHeight()) {
						Gdx.app.log("Clicked: ", "monster");

						if (npc.getHp() > 0) {
							npc.onClick();
						} else{
							npc.isAlive = false;
						}
					}
				}
			}

		}

		for (int i = 0; i < gameObjects.size; i++) {

			Monster npc = gameObjects.get(i);

			if (!npc.isAlive) {
				npc = null;
				gameObjects.removeIndex(i);

			}
		}
	}


	private void drawBackground() {
		batcher.disableBlending();
		batcher.draw(background, 0, 0, 136, gameHeight);
		batcher.enableBlending();

	}
}
