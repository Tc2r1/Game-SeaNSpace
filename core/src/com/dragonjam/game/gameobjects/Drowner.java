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
 * Description: This class handles creating the monster "Drowner"
 * Drowners are the most common monsters, their speed varies, and they have average health.
 *
 */

public class Drowner extends Monster {

	private Random r;
	private static  float ACTOR_HEIGHT = 96.0f;
	private static float ACTOR_WIDTH = 52.0f;
	private Float x = 0f;
	private Float y = 0f;
	private int hp = (int) MathUtils.random(10.0f, 20.f);

	public Drowner(boolean spawnLeft, float yPos, float gameWidth) {
		super(spawnLeft, yPos, gameWidth, AssetLoader.drownerAnimation);
		actorSize = new Vector2(ACTOR_WIDTH, ACTOR_HEIGHT);
		baseSpeed = MathUtils.random(20.0f, 50.0f);
		speedMod = MathUtils.random(.5f, 1.5f);

		y = MathUtils.random(yPos - 10.0f, yPos + 10.0f);
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
				AssetLoader.drownerDie01.play();
				break;
			case 1:
				AssetLoader.drownerDie02.play();
				break;
			case 2:
				AssetLoader.drownerDie02.play();
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
				AssetLoader.drownerHit02.play();
			}

		}

	}

	@Override
	public int getHp() {
		return hp;
	}


}
