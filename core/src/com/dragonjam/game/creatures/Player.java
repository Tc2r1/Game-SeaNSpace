package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Creature {
	
	// The dimensions for each individual frame in
	// the animation / spritesheet for the player
	private final int F_WIDTH = 200;
	private final int F_HEIGHT = 200;
	
	/**
	 * This is the player object for the game.
	 * 
	 * @author Rane
	 */
	public Player() {
		super(100, 10);
		// TODO: Change and balance the health and attack
		
		initAnimation();
	}
	
	@Override
	public void update(float delta) {
		
		this.time = this.time + delta;		
//		if(this.time > animation.getAnimationDuration()) {
//			this.time = 0.0f;
//		}
		
		
		
	}
	
	@Override
	protected void initAnimation() {
		
		Texture spritesheet = new Texture(Gdx.files.internal("images/spritesheets/playerSheet.png"));
		// TODO: Update to the actual size of each frame
		// spriteGrid will break the sprite sheet into each
		// individual "frame". From there, we can load the
		// desired animation(s)
		TextureRegion[][] spriteGrid = TextureRegion.split(spritesheet, F_WIDTH, F_HEIGHT);
		TextureRegion[] frames = new TextureRegion[3];
		
		for(int f = 0; f < 3; f++) {
			frames[f] = spriteGrid[0][f];
		}
		
		animation = new Animation<TextureRegion>(0.33f, frames);
		
	}
	
	// Get / Set methods
	
	
	public int getF_WIDTH() {
		return F_WIDTH;
	}
	
	public int getF_HEIGHT() {
		return F_HEIGHT;
	}
	
}
