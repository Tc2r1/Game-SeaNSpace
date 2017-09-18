package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public abstract class Monster {

	protected int hp;
	protected Texture texture;
	protected float baseSpeed, speedMod;
	protected boolean startLeft;
	protected Vector2 acceleration;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Rectangle collisionBox;
	public boolean isAlive;


	// Constructor for the class

	public Monster(float x, float y, Texture texture, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		this.texture = texture;
		this.baseSpeed = baseSpeed;
		this.startLeft = startLeft;
		this.speedMod = speedMod;
		this.isAlive = true;
		this.hp = hp;
		this.velocity = new Vector2(0, 0);
		this.position = new Vector2(x, y);

		// If we start from opposite side, monster should move in the other direction.
		Gdx.app.log("Monster.java", String.valueOf(startLeft));
		if (startLeft) {

			this.acceleration = new Vector2(baseSpeed, 0);
		} else {
			this.acceleration = new Vector2(-baseSpeed, 0);
		}
	}


	public void update(float delta) {


		// monsters should accelerate based on their direction and speed mod.
		if (startLeft) {
			acceleration.x = baseSpeed * speedMod;
			velocity.add(acceleration.cpy().scl(delta));
			if (velocity.x > 100) {
				velocity.x = 100;
			}
		} else {
			acceleration.x = -baseSpeed * speedMod;
			velocity.add(acceleration.cpy().scl(delta));
			if (velocity.x < -100) {
				velocity.x = -100;
			}
		}

		position.add(velocity.cpy().scl(delta));
	}

	public void onClick() {
	}

	protected abstract void die();

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return new Rectangle(position.x, position.y, 27, 48);
	}

	public abstract int getHp();

	public boolean getIsAlive() {
		return isAlive;
	}

	public abstract boolean collides(Boy boy);

	public abstract boolean collides(Girl girl);

	public void onCollide() {
		if (startLeft) {
			position.add(-100, 0);
		} else {
			position.add(100, 0);
		}
	}

	public boolean isStartLeft() {
		return startLeft;
	}
}
