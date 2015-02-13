package com.tryceo.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.tryceo.gameobjects.Coffee;
import com.tryceo.gameobjects.Grass;
import com.tryceo.gameobjects.ScrollableHandler;


/**
 * Class for updating the game world
 */
public class GameWorld {

    public static float groundPosY;
    public int midPointY;
    ScrollableHandler handler;
    private Coffee coffee;
    private Rectangle ground;

    private boolean touchedDown;
    private int score;

    private GameState currentState;

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        groundPosY = midPointY + 66;
        coffee = new Coffee(Coffee.COFFEE_POS_X, midPointY, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        handler = new ScrollableHandler(this, groundPosY);
        ground = new Rectangle(0, groundPosY, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT);
        score = 0;
        touchedDown = false;
    }

    public void updateRunning(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }

        coffee.update(delta);
        handler.update(delta);

        if (handler.collide(coffee) && coffee.isAlive()) {
            handler.stop();
            coffee.stop();
        }

        if (Intersector.overlaps(coffee.getCircle(), ground)) {
            handler.stop();
            coffee.stop();
            coffee.decelerate();
            currentState = GameState.GAMEOVER;
        }

    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }

    }

    public int getMidPointY() {
        return midPointY;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        score = 0;
        coffee.restart(midPointY);
        handler.restart();
        currentState = GameState.READY;
    }

    public boolean getTouchedDown() {
        return touchedDown;
    }

    public void setTouchedDown(boolean touchedDown) {
        this.touchedDown = touchedDown;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public ScrollableHandler getHandler() {
        return handler;
    }

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
}
