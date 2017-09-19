package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Boy {

	private final Rectangle collisionBox;
	private int width, height;
	private Vector2 position;
	private TextureRegion textureRegion;



	// Constructor for the class
	public Boy(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		collisionBox = new Rectangle();
		textureRegion = AssetLoader.boy;
	}

	public void update(float delta) {

		collisionBox.set(position.x, position.y, width, height);



	}

	public void onDraw(SpriteBatch batch) {
		batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());


	}


	public float getY() {
		return position.y;
	}

	public float getX() {
		return position.x;
	}

	public void onClick(SpriteBatch batch) {
		Gdx.app.log("touch", "touch");
//		batch.setColor(1,1,0,1);
//		batch.draw(textureRegion, getX(), getY(), getWidth(), getHeight());
//		batch.setColor(1,1,1,1);
//		//textureRegion.getTexture().


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
