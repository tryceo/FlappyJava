package com.tryceo.flappyjava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.tryceo.screens.GameScreen;

public class FlappyJava extends Game {
	SpriteBatch batch;

	@Override
	public void create() {
		Gdx.app.log("FlappyJava", "created");
		setScreen(new GameScreen());

	}
}
