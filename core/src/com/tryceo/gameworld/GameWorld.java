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

    ScrollableHandler handler;
    public static float groundPosY;
    public int midPointY;
    private Coffee coffee;
    private Rectangle ground;

    private int score;

    private GameState currentState;

    public enum GameState{
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        groundPosY = midPointY + 66;
        coffee = new Coffee(Coffee.COFFEE_POS_X, midPointY, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        handler = new ScrollableHandler(this, groundPosY);
        ground = new Rectangle(0, groundPosY, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT);
        score = 0;
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
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }

    }

    private void updateReady(float delta) {
        // Do nothing for now
    }


    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

    public boolean isRunning(){
        return currentState == GameState.RUNNING;
    }

    public void start(){
        currentState = GameState.RUNNING;
    }

    public void restart(){
        score = 0;
        coffee.restart(midPointY);
        handler.restart();
        currentState = GameState.READY;
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
}
