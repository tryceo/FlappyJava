package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Class for the pipes in the game
 */
public class Pipe extends Scrollable {

    public static final int PIPE_VERTICAL_GAP = 50;
    public static final int PIPE_WIDTH = 22;
    public static final int PIPE_HEIGHT = 10; //repeating texture height
    public static final int PIPE_TOP_WIDTH = 24;
    public static final int PIPE_TOP_HEIGHT = 14;

    private Random random;
    private Rectangle pipeTopUp, pipeTopDown, pipeUp, pipeDown;

    private boolean scored;

    private float yCenter;


    public Pipe(float x, float y, int width, int height, float scrollSpeed, float yCenter) {
        super(x, y, width, height, scrollSpeed);

        random = new Random();

        pipeTopUp = new Rectangle();
        pipeTopDown = new Rectangle();
        pipeUp = new Rectangle();
        pipeDown = new Rectangle();
        this.yCenter = yCenter;

        scored = false;
    }

    @Override
    public void update(float delta) {

        super.update(delta);

        pipeUp.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        pipeDown.set(this.getX(), this.getY() + this.getHeight() + PIPE_VERTICAL_GAP, this.getWidth(), yCenter - (this.getHeight() + Pipe.PIPE_VERTICAL_GAP));

        pipeTopUp.set(this.getX() - 1, pipeUp.y + pipeUp.height - PIPE_TOP_HEIGHT, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

        pipeTopDown.set(this.getX() - 1, pipeDown.y, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = random.nextInt(90) + 15;
        scored = false;
    }

    public boolean collide(Coffee coffee) {
        return this.getX() < coffee.getX() + coffee.getWidth() && (Intersector.overlaps(coffee.getCircle(), pipeTopDown) || Intersector.overlaps(coffee.getCircle(), pipeTopUp) || Intersector.overlaps(coffee.getCircle(), pipeDown) || Intersector.overlaps(coffee.getCircle(), pipeUp));
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    public boolean hasScored() {
        return scored;
    }

    @Override
    public void restart(float x, float scrollSpeed){
        velocity.x = scrollSpeed;
        reset(x);
    }
}
