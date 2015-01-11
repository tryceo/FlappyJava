package com.tryceo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tryceo.gameworld.GameRenderer;
import com.tryceo.gameworld.GameWorld;
import com.tryceo.helpers.InputHandler;

/**
 * Class for the main screen for the game
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    public static final int GAME_WIDTH = 136;

    public GameScreen() {
        int midPointY = (Gdx.graphics.getHeight() / (Gdx.graphics.getWidth() / GAME_WIDTH)) / 2;
        world = new GameWorld(midPointY);

        runTime = 0f;
        renderer = new GameRenderer(world, midPointY, Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(new InputHandler(world));
        Gdx.app.log("GameScreen", "attached");

    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        runTime += delta;
        renderer.render(runTime);

        Gdx.app.log("FPS", 1 / delta + "");
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resize (" + width + ", " + height + ") called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "dispose called");
    }
}
