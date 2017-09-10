package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Monster {

	protected int width, height, hp;
	protected float baseSpeed, speedMod;
	protected boolean startLeft;
	protected Vector2 acceleration;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Rectangle collisionBox;


	// Constructor for the class


	public Monster(float x, float y, int width, int height, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		this.width = width;
		this.height = height;
		this.baseSpeed = baseSpeed;
		this.startLeft = startLeft;
		this.speedMod = speedMod;
		this.acceleration = new Vector2(baseSpeed, 0);
		this.velocity = new Vector2(0, 0);
		this.position = new Vector2(x, y);
		collisionBox = new Rectangle();

	}

	public void update(float delta) {
		acceleration.x = baseSpeed * speedMod;
		velocity.add(acceleration.cpy().scl(delta));
		Gdx.app.log("velocity = ", velocity + "");
		if (velocity.x > 200) {
			velocity.x = 200;
		}

		position.add(velocity.cpy().scl(delta));
		collisionBox.set(position.x, position.y, width, height);

	}

	public void onClick() {

	}

	public float getY() {
		return position.y;
	}

	public float getX() {
		return position.x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}
}
