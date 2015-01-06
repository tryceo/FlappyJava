package com.tryceo.flappyjava;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tryceo.helpers.AssetLoader;
import com.tryceo.screens.GameScreen;

/**
 * Main class to start the game
 */
public class FlappyJava extends Game {
    SpriteBatch batch;

    @Override
    public void create() {
        Gdx.app.log("FlappyJava", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    public void dispose() {
        AssetLoader.dispose();
        super.dispose();
    }
}
