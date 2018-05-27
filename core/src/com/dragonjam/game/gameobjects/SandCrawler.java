package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: This class handles creating the monster "Sand Crawler"
 * Sandcrawler stays low to the ground, moves slowly, and has a bit more health than other monsters.
 */

public class SandCrawler extends Monster {

	private Random r;
	private static  float ACTOR_HEIGHT = 48.0f;
	private static float ACTOR_WIDTH = 27.0f;
	private Float x = 0f;
	private Float y = 0f;
	private int hp = 40;

	public SandCrawler(boolean spawnLeft, float yPos, float gameWidth) {
		super(spawnLeft, yPos, gameWidth, AssetLoader.sandCrawlerAnimation);
		actorSize = new Vector2(ACTOR_WIDTH, ACTOR_HEIGHT);
		baseSpeed = MathUtils.random(5.0f, 12.0f);
		speedMod = MathUtils.random(1.0f, 1.3f);

		y = MathUtils.random(yPos - 25.0f, yPos - 15.0f);
		if (spawnLeft) {
			x = MathUtils.random(-20, 3.0f);
		} else {
			// spawns from right side of the map.
			x = MathUtils.random(gameWidth - 5.0f, gameWidth + 10.0f);

		}
		position = new Vector2(x, y);
		r = new Random();
	}


	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	protected void die() {
		switch(r.nextInt(3)) {
			case 0:
				AssetLoader.sandCrawlerDie01.play();
				break;
			case 1:
				AssetLoader.sandCrawlerDie01.play();
				break;
			case 2:
				AssetLoader.sandCrawlerDie01.play();
				break;
		}
		isAlive = false;
		velocity.y = 0;
		velocity.x = 0;
		acceleration.x = 0;
		//texture.dispose();

	}


	public boolean collides(Boy boy) {
		// check if collision occurs with boy.
		if (startLeft) {
			if (position.x < boy.getX() + boy.getWidth()) {

				// Boy Hit!
				// push monster back, play sound effect. lower boy hp

				return (Intersector.overlaps(boy.getCollisionBox(), getBounds()));
			}
		}else{
			if (position.x > boy.getX()) {
				return (Intersector.overlaps(boy.getCollisionBox(), getBounds()));

				// Boy Hit!
			}
		}
		return false;
	}

	public boolean collides(Girl girl) {
		// check if collision occurs with boy.
		if (position.x < girl.getX() + girl.getWidth()) {
			return (Intersector.overlaps(girl.getCollisionBox(), getBounds()));

			// Girl Hit!
		}
		return false;
	}

	@Override
	public void onCollide() {
		super.onCollide();
		setVelocity(new Vector2(0, 0));

	}

	public void onClick(SpriteBatch batch, float delta) {
		super.onClick(batch, delta);

		// Subtract HP
		if (this.hp > 0 && isAlive) {
			this.hp -= 10;

			// Play Sound Effect
			if (r.nextInt(2) == 0) {
				AssetLoader.drownerHit.play();
			} else {
				AssetLoader.sandCrawlerDie01.play();
			}

		}

	}

	@Override
	public int getHp() {
		return hp;
	}


}
