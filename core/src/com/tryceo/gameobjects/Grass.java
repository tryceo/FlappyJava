package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class for the grass on the ground
 */
public class Grass extends Scrollable{

    public static final int GRASS_WIDTH =143;
    public static final int GRASS_HEIGHT = 11;

    public Grass(float x, float y, int width, int height, float scrollSpeed){
        super(x, y, width, height, scrollSpeed);
    }

    public boolean collide (Coffee coffee){
        return (coffee.getY() > position.y);
    }

}
