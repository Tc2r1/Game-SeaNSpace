package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	protected Vector2 actorSize;
	private Animation mobAnimation;
	public boolean isAlive;


	// Constructor for the class
	public Monster(boolean startLeft, float yPos, float gameWidth, Animation mobAnimation) {
		this.startLeft = startLeft;
		this.isAlive = true;
		this.velocity = new Vector2(0, 0);
		this.mobAnimation = mobAnimation;

		// If we start from opposite side, monster should move in the other direction.
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


	public void onDraw(SpriteBatch batch, float delta) {
		batch.draw((TextureRegion) mobAnimation.getKeyFrame(delta), isStartLeft() ? getBounds().x : getBounds().x + getBounds().getWidth(), getBounds().y, isStartLeft() ? getBounds().getWidth() : -getBounds().getWidth(), getBounds().getHeight());
	}

	public void onClick(SpriteBatch batch, float delta) {
		Gdx.app.log("touch", "touch");
		batch.setColor(1, 0, 0, 1);
		batch.draw((TextureRegion) mobAnimation.getKeyFrame(delta), isStartLeft() ? getBounds().x : getBounds().x + getBounds().getWidth(), getBounds().y, isStartLeft() ? getBounds().getWidth() : -getBounds().getWidth(), getBounds().getHeight());
		batch.setColor(1, 1, 1, 1);
	}


	protected abstract void die();

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return new Rectangle(position.x, position.y, actorSize.x, actorSize.y);
	}

	public abstract int getHp();

	public boolean getIsAlive() {
		return isAlive;
	}

	public abstract boolean collides(Boy boy);

	public abstract boolean collides(Girl girl);

	public void onCollide() {
		if (startLeft) {
			position.add(-300, 0);
		} else {
			position.add(300, 0);
		}
	}

	public boolean isStartLeft() {
		return startLeft;
	}

}
