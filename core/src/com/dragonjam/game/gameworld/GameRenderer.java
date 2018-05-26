package com.dragonjam.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.gameobjects.Boy;
import com.dragonjam.game.gameobjects.Fish;
import com.dragonjam.game.gameobjects.FishHandler;
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
	private FishHandler fishHandler;

	private Array<Monster> monsters;
	private Array<Fish> fishs;

	// Game Assets
	private TextureRegion background;
	private TextureRegion girlTR, boyTR;

	// Game Variables
	private boolean objectClicked = false;


	public GameRenderer(GameWorld gameWorld, int gameWidth, int gameHeight, int midPointX) {

		this.gameWorld = gameWorld;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.midPointX = midPointX;
		monsterHandler = gameWorld.getMonsterHandler();
		fishHandler = gameWorld.getFishHandler();
		monsters = new Array<Monster>();
		fishs = new Array<Fish>();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, gameWidth, 360);
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

	}

	private void initGameObjects() {

		boy = gameWorld.getBoy();
		girl = gameWorld.getGirl();
		monsterHandler = gameWorld.getMonsterHandler();
		monsters = monsterHandler.getMonsters();
		fishHandler = gameWorld.getFishHandler();
		fishs = fishHandler.getFishs();
		touchPos = new Vector3();
	}
	public OrthographicCamera getCam(){
		return cam;
	}

	public void render(float runTime) {


		// Clear the background

		Gdx.gl.glClearColor(255, 255, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		objectClicked = false;


		// Begin boyBatcher
		batcher.begin();

		// Draw Background
		drawBackground();

		// Draw MainCharacters.
		girl.onDraw(batcher);
		boy.onDraw(batcher);
		//batcher.draw(girlTR, girl.getX(), girl.getY(), girl.getWidth(), girl.getHeight());


		// Draw monsters.
		for (Monster mob : monsters) {
			mob.onDraw(batcher, runTime);
		}

		// Draw fish.
		for (Fish fish : fishs) {
			fish.onDraw(batcher, runTime);
		}


		// See collision Boxes.
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.RED);
//		shapeRenderer.rect(boy.getCollisionBox().x, boy.getCollisionBox().y, boy.getCollisionBox().width, boy.getCollisionBox().height);
//
//		shapeRenderer.setColor(Color.PINK);
//		shapeRenderer.rect(girl.getCollisionBox().x, girl.getCollisionBox().y, girl.getCollisionBox().width, girl.getCollisionBox().height);

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
					boy.onClick(batcher);

				}
			}



			for (Monster npc : monsters) {
				if (!objectClicked && touchPos.x > npc.getBounds().getX() && touchPos.x < npc.getBounds().getX() + npc.getBounds().getWidth()) {
					if (!objectClicked && touchPos.y > npc.getBounds().getY() && touchPos.y < npc.getBounds().getY() + npc.getBounds().getHeight()) {
						Gdx.app.log("Clicked: ", "monster");
						if (npc.getHp() > 0) {
							npc.onClick(batcher, runTime);

						} else {
							npc.isAlive = false;

						}
						objectClicked = true;
						break;
					}
				}
			}

		}

		for (int i = 0; i < monsters.size; i++) {

			Monster npc = monsters.get(i);

			if (!npc.isAlive) {
				npc = null;
				monsters.removeIndex(i);

			}
		}

		// Display Score:

		String score = gameWorld.getScore() + "";

		// Draw shadow first.
		// Draw shadow first
		batcher.end();
		batcher.begin();
		AssetLoader.shadow.draw(batcher, score, gameWidth/2 - (3*score.length()), gameHeight - 51);
		// Draw text
		AssetLoader.font.draw(batcher, score, gameWidth/2 - (3*score.length()-1), gameHeight - 50);
		batcher.end();
	}


	private void drawBackground() {
		batcher.disableBlending();
		batcher.draw(background, 0, 0, gameWidth, 360);
		batcher.enableBlending();

	}
}
