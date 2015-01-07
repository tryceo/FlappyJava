package com.tryceo.gameobjects;

import com.tryceo.gameworld.GameWorld;

/**
 * Handles the scrolling objects like pipe and grass
 */
public class ScrollableHandler {

    public static final int SCROLL_SPEED = -59; //experimentally determined constants
    public static final int PIPE_HORIZONTAL_GAP = 49; //experimentally determined constants
    private Grass grass1, grass2;
    private Pipe pipe1, pipe2, pipe3;
    private GameWorld world;


    public ScrollableHandler(GameWorld world, float yCenter) {

        grass1 = new Grass(0, yCenter, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT, SCROLL_SPEED);
        grass2 = new Grass(grass1.getTailX(), yCenter, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT, SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, Pipe.PIPE_WIDTH, 60, SCROLL_SPEED, yCenter);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_HORIZONTAL_GAP, 0, Pipe.PIPE_WIDTH, 70, SCROLL_SPEED, yCenter);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_HORIZONTAL_GAP, 0, Pipe.PIPE_WIDTH, 60, SCROLL_SPEED, yCenter);
        this.world = world;
    }

    public void update(float delta) {
        grass1.update(delta);
        grass2.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        if (!pipe1.hasScored() && pipe1.getTailX() < Coffee.COFFEE_POS_X) {
            pipe1.setScored(true);
            world.addScore(1);

        } else if (!pipe2.hasScored() && pipe2.getTailX() < Coffee.COFFEE_POS_X) {
            pipe2.setScored(true);
            world.addScore(1);

        } else if (!pipe3.hasScored() && pipe3.getTailX() < Coffee.COFFEE_POS_X) {
            pipe3.setScored(true);
            world.addScore(1);
        }

        if (pipe1.isScrollLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_HORIZONTAL_GAP);//if pipe1 is off screen, reset its position to the right of pipe3
        } else if (pipe2.isScrollLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_HORIZONTAL_GAP);//reset pipe2 to right of pipe1
        } else if (pipe3.isScrollLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_HORIZONTAL_GAP);//rest pipe3 to right of pipe2
        }

        if (grass1.isScrollLeft()) {
            grass1.reset(grass2.getTailX());//reset grass1 to the right of grass2
        } else if (grass2.isScrollLeft()) {
            grass2.reset(grass1.getTailX());//reset grass2 to the right of grass1
        }
    }

    public void stop() {
        grass1.stop();
        grass2.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    public boolean collide(Coffee coffee) {
        return (pipe1.collide(coffee) || pipe2.collide(coffee) || pipe3.collide(coffee));
    }

    public void restart(){
        grass1.restart(0, SCROLL_SPEED);
        grass2.restart(grass1.getTailX(), SCROLL_SPEED);
        pipe1.restart(210, SCROLL_SPEED);
        pipe2.restart(pipe1.getTailX() + PIPE_HORIZONTAL_GAP, SCROLL_SPEED);
        pipe3.restart(pipe2.getTailX() + PIPE_HORIZONTAL_GAP, SCROLL_SPEED);
    }

    public Grass getGrass1() {
        return grass1;
    }

    public Grass getGrass2() {
        return grass2;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }
}
