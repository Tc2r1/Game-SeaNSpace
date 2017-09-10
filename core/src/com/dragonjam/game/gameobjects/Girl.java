package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Girl {

	private int width, height;
	private Vector2 position;
	private Rectangle collisionBox;


	// Constructor for the class
	public Girl(float x, float y, int width, int height) {
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
