package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Class for the pipes in the game
 */
public class Pipe extends Scrollable{

    private Random random;
    public static final int PIPE_VERTICAL_GAP = 45;
    public static final int PIPE_WIDTH = 22;
    public static final int PIPE_TOP_WIDTH = 24;
    public static final int PIPE_TOP_HEIGHT = 11;


    private Rectangle pipeUp, pipeDown, barUp, barDown;

    private float yCenter;



    public Pipe (float x, float y, int width, int height, float scrollSpeed, float yCenter){
        super(x, y, width, height, scrollSpeed);

        random = new Random();

        pipeUp = new Rectangle();
        pipeDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.yCenter = yCenter;
    }

    @Override
    public void update(float delta){

        super.update(delta);

        barUp.set(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        barDown.set(this.getX(), this.getY() + this.getHeight() + PIPE_VERTICAL_GAP, this.getWidth(), yCenter - (this.getHeight() + Pipe.PIPE_VERTICAL_GAP));

        pipeUp.set(this.getX()-1, barUp.y + barUp.height - PIPE_TOP_HEIGHT, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

        pipeDown.set(this.getX()-1, barDown.y, PIPE_TOP_WIDTH, PIPE_TOP_HEIGHT);

    }

    @Override
    public void reset(float newX){
        super.reset(newX);
        height = random.nextInt(90) + 15;
    }

    public boolean collide(Coffee coffee){
        if (this.getX() < coffee.getX() + coffee.getWidth()){
            return (Intersector.overlaps(coffee.getCircle(), pipeDown) || Intersector.overlaps(coffee.getCircle(), pipeUp) ||
                    Intersector.overlaps(coffee.getCircle(),barDown) || Intersector.overlaps(coffee.getCircle(),barUp));
        }
        return false;
    }

    public Rectangle getPipeUp() {
        return pipeUp;
    }

    public Rectangle getPipeDown() {
        return pipeDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
}
