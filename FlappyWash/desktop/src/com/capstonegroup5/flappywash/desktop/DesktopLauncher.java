package com.capstonegroup5.flappywash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.capstonegroup5.flappywash.FlappyWash;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyWash.WIDTH;
		config.height = FlappyWash.HEIGHT;
		config.title = FlappyWash.TITLE;
		new LwjglApplication(new FlappyWash(), config);
	}
}
