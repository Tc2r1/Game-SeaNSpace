package com.dragonjam.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description: Handles the loading of assets.
 */

public class AssetLoader {

	public static Texture backgroundTexture, boyTexture;
	public static Texture drownerTexture;
	public static Texture eatingTexture;
	public static Texture girlTexture;

	public static Animation monsterAnimation;

	public static TextureRegion background;
	public static TextureRegion boy;
	public static TextureRegion girl;
	public static TextureRegion monsterAnim1, monsterAnim2, monsterAnim3,
					monsterAnim4, monsterAnim5, monsterAnim6;
	public static TextureRegion monsterEating;

	public static void load() {


		// Load the textures from the images.
		boyTexture = new Texture(Gdx.files.internal("images/sprites/boy.png"));
		girlTexture = new Texture(Gdx.files.internal("images/sprites/girl.png"));
		drownerTexture = new Texture(Gdx.files.internal("images/sprites/drowner.png"));
		eatingTexture = new Texture(Gdx.files.internal("images/sprites/eating.png"));

		backgroundTexture = new Texture(Gdx.files.internal("images/background.png"));
		//backgroundTexture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

		// Add the background Texture Region.
		background = new TextureRegion(backgroundTexture);
		background.flip(true, false);


		// Add boy and girl texture regions.
		boy = new TextureRegion(boyTexture);

		girl = new TextureRegion(girlTexture);


		// monster eating Texture.
		monsterEating = new TextureRegion(eatingTexture);


		// create the animations.

		monsterAnim1 = new TextureRegion(drownerTexture, 2, 2, 300, 450);


		monsterAnim2 = new TextureRegion(drownerTexture, 304, 2, 300, 450);


		monsterAnim3 = new TextureRegion(drownerTexture, 606, 3, 300, 449);


		monsterAnim4 = new TextureRegion(drownerTexture, 1512, 8, 296, 444);


		monsterAnim5 = new TextureRegion(drownerTexture, 908, 4, 300, 448);


		monsterAnim6 = new TextureRegion(drownerTexture, 1210, 4, 300, 448);


		TextureRegion[] drowners = {monsterAnim1, monsterAnim2, monsterAnim3, monsterAnim4, monsterAnim5, monsterAnim6};

		// Animates at .06 seconds per complete animation.
		monsterAnimation = new Animation(0.06f, drowners);

		// pingpong adds a springy effect.
		monsterAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

	}

	public static void dispose() {
		// Dispose of all textures when we are done using them.
		boyTexture.dispose();
		girlTexture.dispose();
		drownerTexture.dispose();
		eatingTexture.dispose();
		backgroundTexture.dispose();

	}

}
