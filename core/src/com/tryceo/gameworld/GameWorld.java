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
    private Coffee coffee;
    private Rectangle ground;
    private int score;

    public GameWorld(int midPointY) {
        coffee = new Coffee(Coffee.COFFEE_POS_X, midPointY - 5, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        handler = new ScrollableHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT);
        score = 0;
    }

    public void update(float delta) {

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
        }

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
