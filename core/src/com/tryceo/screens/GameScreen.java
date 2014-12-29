package com.tryceo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.tryceo.gameworld.GameRenderer;
import com.tryceo.gameworld.GameWorld;

/**
 * Created by Jack on 12/29/2014.
 */
public class GameScreen implements Screen{

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(){
        world = new GameWorld();
        renderer = new GameRenderer(world);
        Gdx.app.log("GameScreen", "attached");

    }
    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resize ("+width+", "+height+ ") called");
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
