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

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public abstract class Fish {

    private static final String TAG = "FISH";
    ;
    public State currentState, previousState;
    public boolean isAlive;
    protected int hp;
    protected Texture texture;
    protected float baseSpeed = 10;
    protected float speedMod = .5f;
    protected boolean startLeft;
    protected Vector2 acceleration;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 actorSize;
    private float stateTimer;
    private float swimDecisionsTimer;
    private int swimDecision;
    private float x, y, width, height;
    private Random r;
    private boolean isSwimming;
    private Rectangle collisionBox;
    private TextureRegion currentRegion;
    private Animation<TextureRegion> idleAnimation, swimAnimation;
    private float maxSpawnWidth;
    private Animation mobAnimation;


    // Constructor for the class
    public Fish(boolean startLeft, float maxSpawnHeight, float maxSpawnWidth) {
        y = MathUtils.random(maxSpawnHeight);
        if (startLeft) {
            x = MathUtils.random(-20, maxSpawnWidth / 2);
        } else {
            // spawns from right side of the map.
            x = MathUtils.random(maxSpawnWidth / 2, maxSpawnWidth);
        }
        position = new Vector2(x, y);
        collisionBox = new Rectangle();
        this.startLeft = startLeft;
        this.isAlive = true;
        this.velocity = new Vector2(0, 0);
        this.mobAnimation = AssetLoader.fishBlackIdle;
        this.actorSize = new Vector2(27, 27);
        this.maxSpawnWidth = maxSpawnWidth;
        r = new Random();


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
        swimDecisionsTimer = 0;
        isSwimming = false;

        // initialize Animations.
        swimAnimation = AssetLoader.fishBlackSwim;
        idleAnimation = AssetLoader.fishBlackIdle;
    }

    public void update(float delta) {
        currentRegion = getFrame(delta);

        // monsters should accelerate based on their direction and speed mod.
        if (currentState == State.SWIMMING) {

            swimDecisionsTimer += delta;

            // if on the left side, turn around.
            if (position.x < 0) {

                acceleration.x = baseSpeed * speedMod;
                velocity.set(acceleration.x, acceleration.y);
                if (velocity.x > 50) {
                    velocity.x = 50;
                }

            }
            // if on the right side, turn around.
            if (position.x > maxSpawnWidth) {

                Gdx.app.log("Swim Left: ", "Swim Left!");
                acceleration.x = -baseSpeed * speedMod;
                velocity.set(acceleration.x, acceleration.y);
                if (velocity.x > -50) {
                    velocity.x = -50;
                }
            }

            // If time for Fish to make an action.
            if (swimDecisionsTimer > 4) {
                Gdx.app.log("new Action", "new action Time");
                swimDecisionsTimer = 0;
                swimDecision = r.nextInt(2);
                velocity.set(0,0);
            }

            if (position.x > 0 && position.x < maxSpawnWidth) {
                switch (swimDecision) {
                    case 0:
                        Gdx.app.log("Int ", 0 + " chosen");

                        // Swim Right!
                        acceleration.x = baseSpeed * speedMod;
                        velocity.add(acceleration.cpy().scl(delta));
                        if (velocity.x > 50) {
                            velocity.x = 50;
                        }
                        break;
                    case 1:
                        Gdx.app.log("Int ", 1 + " chosen");

                        // Swim Left!
                        acceleration.x = -baseSpeed * speedMod;
                        velocity.add(acceleration.cpy().scl(delta));
                        if (velocity.x < -50) {
                            velocity.x = -50;
                        }
                        break;
                    case 2:
                        Gdx.app.log("Int ", 2 + " chosen");
                        break;
                    case 3:
                        Gdx.app.log("Int ", 3 + " chosen");
                        break;


                }
            }
            position.add(velocity.cpy().scl(delta));
        }

        collisionBox.set(position.x, position.y, width, height);
    }

    public void onDraw(SpriteBatch batch, float delta) {
        batch.draw((TextureRegion) mobAnimation.getKeyFrame(delta), isStartLeft() ? getBounds().x : getBounds().x + getBounds().getWidth(), getBounds().y, isStartLeft() ? getBounds().getWidth() : -getBounds().getWidth(), getBounds().getHeight());
    }

    public Fish.State getState() {
        if (isSwimming) {
            return State.SWIMMING;
        } else {
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
                    Gdx.app.log("Fish", "Swim Time");
                    stateTimer = 0;
                    startSwimming();
                }
                break;

        }


//		// set facing of girl based on isFaceRight switch.
//		if (isFaceRight && region.isFlipX() == true) {
//			region.flip(true, false);
//		}
//		if (!isFaceRight && region.isFlipX() == false){
//			region.flip(true, false);
//		}

        // update the stateTime and previous state. if state has changed, reset state time.
        if (currentState == previousState) {
            stateTimer = stateTimer + delta;
        } else {
            stateTimer = 0;
        }
        previousState = currentState;

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

    public void startSwimming() {
        isSwimming = true;
    }

    public boolean isStartLeft() {
        return startLeft;
    }

    protected enum State {IDLE, SWIMMING}

}
