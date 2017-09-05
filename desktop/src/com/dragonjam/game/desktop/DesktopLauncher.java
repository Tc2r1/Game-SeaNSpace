package com.dragonjam.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dragonjam.game.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(40 * 9, 40 * 19);
		config.setWindowSizeLimits(350, 350, 1440, 2560);
		new Lwjgl3Application(new GameMain(), config);
	}
}
