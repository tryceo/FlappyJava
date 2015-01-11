package com.tryceo.gameobjects;

/**
 * Class for the grass on the ground
 */
public class Grass extends Scrollable {

    public static final int GRASS_WIDTH = 165;
    public static final int GRASS_HEIGHT = 11;


    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

}
