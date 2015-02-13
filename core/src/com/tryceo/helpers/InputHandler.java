package com.tryceo.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.tryceo.gameobjects.Coffee;
import com.tryceo.gameworld.GameWorld;
import com.tryceo.screens.GameScreen;

/**
 * Class for handling the clicking events
 */
public class InputHandler implements InputProcessor {


    private Coffee coffee;
    private GameWorld world;

    public InputHandler(GameWorld world) {
        this.world = world;
        coffee = world.getCoffee();
    }

    @Override
    public boolean keyDown(int keycode) {

        Gdx.app.log("keyboard pos", ""+keycode);
        if (keycode == 62){
            if (world.isReady()) {
                world.setTouchedDown(true);
            }
            else if (world.isRunning())
                coffee.onClick();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (world.isReady() && keycode == 62) {
            world.start();
            world.setTouchedDown(false);
        } else if (world.isGameOver() && keycode == 62){
            world.restart();
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Gdx.app.log("touch pos", screenX + " , " + screenY);
        if (world.isReady() && playButtonTouch(screenX, screenY)){
            world.setTouchedDown(true);
        }
        if (world.isRunning()) {
            coffee.onClick();
        }

        return true;
    }

    private boolean playButtonTouch(int screenX, int screenY){
        double ratioX = Gdx.graphics.getWidth()/ GameScreen.GAME_WIDTH;
        double newX = screenX/ratioX;
        double newY = screenY/ratioX;

       return ((newX > 68 - (AssetLoader.gamePlayButtonNormal.getRegionWidth()/2))
       && (newX < 68 + (AssetLoader.gamePlayButtonNormal.getRegionWidth()/2))
       && (newY > world.getMidPointY() + 20)
       && (newY < world.getMidPointY() + 20 + AssetLoader.gamePlayButtonNormal.getRegionHeight()));
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (world.isReady()){
            world.setTouchedDown(false);
            if (playButtonTouch(screenX, screenY)){
                world.start();
            }
        }

        if (world.isGameOver()){
            world.restart();
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
