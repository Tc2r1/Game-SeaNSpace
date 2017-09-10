package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Boy {

	private final Rectangle collisionBox;
	private int width, height;
	private Vector2 position;


	// Constructor for the class
	public Boy(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		collisionBox = new Rectangle();
	}

	public void update(float delta) {

		collisionBox.set(position.x, position.y, width, height);

	}


	public float getY() {
		return position.y;
	}

	public float getX() {
		return position.x;
	}

	public void onClick() {

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
