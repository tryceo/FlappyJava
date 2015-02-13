package com.tryceo.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
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
        float ratioX = (float)Gdx.graphics.getWidth()/ GameScreen.GAME_WIDTH;
        float ratioY = (float)Gdx.graphics.getHeight()/ GameScreen.GAME_HEIGHT;

        float scaledCenterX = ratioX * GameScreen.GAME_WIDTH/2;
        float scaledCenterY = ratioY * (world.getMidPointY() + 20);

        float scaledRegionHalfWidth = ratioX * (AssetLoader.gamePlayButtonNormal.getRegionWidth()/2f);
        float scaledRegionHalfHeight = ratioY * (AssetLoader.gamePlayButtonNormal.getRegionHeight()/2f);

        Rectangle playButtonBounds = new Rectangle(scaledCenterX - scaledRegionHalfWidth, scaledCenterY , scaledRegionHalfWidth*2, scaledRegionHalfHeight*2);

        return playButtonBounds.contains(screenX, screenY);
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
