package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerWhole {
	
	// Use the attacker's health and attack for
	// evaluating enemy damage
	private PlayerAttacker girl;
	private PlayerFisher boy;
	
	/**
	 * This is the Player object of the game that
	 * holds any and all creatures than the player
	 * can control (i.e. fisher, attacker, etc.)
	 * 
	 * @author Rane
	 */
	public PlayerWhole() {
		
		girl = new PlayerAttacker();
		boy = new PlayerFisher();
		
	}
	
	/**
	 * Will update the separate creatures within
	 * the player's control, as well as any other
	 * operations that need to be handled.
	 * 
	 * @param delta
	 * time since the last frame was rendered
	 * 
	 * @author Rane
	 */
	public void update(float delta) {
				
		girl.update(delta);
		boy.update(delta);
		
	}
	
	/**
	 * Will return the current frame in the attacker's
	 * animation based on the object's <code>time</code>
	 * variables.
	 * 
	 * @return
	 * A TextureRegion object representing the current
	 * frame.
	 */
	public TextureRegion getAttackerTexture() {
		return girl.getAnimation().getKeyFrame(girl.getTime(), true);
	}
	
	/**
	 * Will return the current frame in the fisher's
	 * animation based on the object's <code>time</code>
	 * variables.
	 * 
	 * @return
	 * A TextureRegion object representing the current
	 * frame.
	 */
	public TextureRegion getFisherTexture() {
		return boy.getAnimation().getKeyFrame(boy.getTime(), true);
	}
	
	// Get / Set methods
	
	
	public PlayerAttacker getAttacker() {
		return girl;
	}
	
	public PlayerFisher getFisher() {
		return boy;
	}
	
	public double getAttack() {
		return girl.getAttack();
	}
	
	public double getHealth() {
		return girl.getHealth();
	}
	
}
