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
	private Array<Monster> monsters;

	// Game Assets
	private TextureRegion background;
	private Animation mobAnimation;
	private TextureRegion girlTR, boyTR;


	public GameRenderer(GameWorld gameWorld, int gameWidth, int gameHeight, int midPointX) {

		this.gameWorld = gameWorld;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.midPointX = midPointX;
		monsterHandler = gameWorld.getMonsterHandler();
		monsters = new Array<Monster>();

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
		monsters = monsterHandler.getMonsters();
		touchPos = new Vector3();
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
		for (Monster mob : monsters) {
			batcher.draw((TextureRegion) mobAnimation.getKeyFrame(runTime), mob.isStartLeft() ? mob.getBounds().x : mob.getBounds().x + mob.getBounds().getWidth(), mob.getBounds().y, mob.isStartLeft() ? mob.getBounds().getWidth() : -mob.getBounds().getWidth(), mob.getBounds().getHeight());
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


			for (Monster npc : monsters) {
				if (touchPos.x > npc.getBounds().getX() && touchPos.x < npc.getBounds().getX() + npc.getBounds().getWidth()) {
					if (touchPos.y > npc.getBounds().getY() && touchPos.y < npc.getBounds().getY() + npc.getBounds().getHeight()) {
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
		batcher.draw(background, 0, 0, 136, gameHeight);
		batcher.enableBlending();

	}
}
