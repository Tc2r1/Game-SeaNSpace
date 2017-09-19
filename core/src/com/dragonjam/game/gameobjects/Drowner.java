package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Drowner extends Monster {

	private Random r;
	private int hp;
	private boolean isAlive = true;


	public Drowner(float x, float y, Vector2 pos, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		super(x, y, AssetLoader.drownerTexture, baseSpeed, speedMod, startLeft, hp);


		isAlive = true;
		this.hp = hp;

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
				AssetLoader.zombiedie01.play();
				break;
			case 1:
				AssetLoader.zombiedie02.play();
				break;
			case 2:
				AssetLoader.zombiedie03.play();
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
				AssetLoader.zombieHit01.play();
			} else {
				AssetLoader.zombieHit02.play();
			}

		}

	}

	@Override
	public int getHp() {
		return hp;
	}


}
