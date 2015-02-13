package com.tryceo.flappyjava.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tryceo.flappyjava.FlappyJava;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flappy Java";
		config.height = 408;
		config.width = 272;
		new LwjglApplication(new FlappyJava(), config);
	}
}
