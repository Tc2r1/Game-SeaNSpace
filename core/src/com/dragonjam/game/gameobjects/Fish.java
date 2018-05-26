package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public abstract class Fish {

	protected enum State { IDLE, SWIMMING};
	public State currentState, previousState;
	private float stateTimer;
	private static final String TAG = "FISH";
	private float x, y, width, height;
	private boolean isSwimming;
	private Rectangle collisionBox;
	private TextureRegion currentRegion;
	private Animation<TextureRegion> idleAnimation, swimAnimation;
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

	public Fish(float x, float y, Texture texture, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		this.texture = texture;
		this.baseSpeed = baseSpeed;
		this.startLeft = startLeft;
		this.speedMod = speedMod;
		this.isAlive = true;
		this.hp = hp;
		this.velocity = new Vector2(0, 0);
		this.position = new Vector2(x, y);
		this.mobAnimation = AssetLoader.fishBlackIdle;
		collisionBox = new Rectangle();

		// If we start from opposite side, monster should move in the other direction.
		if (startLeft) {

			this.acceleration = new Vector2(baseSpeed, 0);
		} else {
			this.acceleration = new Vector2(-baseSpeed, 0);
		}

		// initialize state variables
		currentState = State.IDLE;
		previousState = State.IDLE;
		stateTimer = 0;
		isSwimming = false;

		// initialize Animations.
		swimAnimation = AssetLoader.fishBlackSwim;
		idleAnimation = AssetLoader.fishBlackIdle;
	}

	public Fish(boolean startLeft, float maxSpawnHeight, float maxSpawnWidth) {
		y = MathUtils.random(maxSpawnHeight);
		if (startLeft) {
			x = MathUtils.random(-20, maxSpawnWidth/2);
		} else {
			// spawns from right side of the map.
			x = MathUtils.random(maxSpawnWidth/2, maxSpawnWidth);
		}
		position = new Vector2(x, y);
		collisionBox = new Rectangle();
		this.startLeft = startLeft;
		this.isAlive = true;
		this.velocity = new Vector2(0, 0);
		this.mobAnimation = AssetLoader.fishBlackIdle;
		this.actorSize = new Vector2(14, 14);

		// If we start from opposite side, monster should move in the other direction.
		if (startLeft) {

			this.acceleration = new Vector2(baseSpeed, 0);
		} else {
			this.acceleration = new Vector2(-baseSpeed, 0);
		}

		// initialize state variables
		currentState = State.IDLE;
		previousState = State.IDLE;
		stateTimer = 0;
		isSwimming = false;

		// initialize Animations.
		swimAnimation = AssetLoader.fishBlackSwim;
		idleAnimation = AssetLoader.fishBlackIdle;
	}


	public void update(float delta) {
		currentRegion = getFrame(delta);

		// monsters should accelerate based on their direction and speed mod.
		if (currentState == State.SWIMMING) {
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

		collisionBox.set(position.x, position.y, width, height);
	}


	public void onDraw(SpriteBatch batch, float delta) {
		batch.draw((TextureRegion) mobAnimation.getKeyFrame(delta), isStartLeft() ? getBounds().x : getBounds().x + getBounds().getWidth(), getBounds().y, isStartLeft() ? getBounds().getWidth() : -getBounds().getWidth(), getBounds().getHeight());
	}

	public Fish.State getState(){
		if(isSwimming){
			return State.SWIMMING;
		} else
		{
			return State.IDLE;
		}
	}

	public TextureRegion getFrame(float delta) {
		// Get the curent state of the boy.
		currentState = getState();
		TextureRegion region = new TextureRegion();

		switch (currentState) {
			case SWIMMING:
				region = swimAnimation.getKeyFrame(stateTimer);
				if (stateTimer > 5 && previousState == State.SWIMMING) {
					isSwimming = false;
				}
				break;
			case IDLE:
			default:
				region = idleAnimation.getKeyFrame(stateTimer);
				if (stateTimer > 5 && previousState == State.IDLE) {
					startSwimming();
				}
				break;

		}

		return region;
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

	public void startSwimming(){
		isSwimming = true;
	}

	public boolean isStartLeft() {
		return startLeft;
	}

}
