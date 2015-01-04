package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Base class for scrollable object like pipe and grass
 */
public class Scrollable {

    Vector2 position;
    Vector2 velocity;
    int width;
    int height;
    boolean isScrollLeft;

    public  Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrollLeft = false;
    }

    public void update (float delta){
        position.add(velocity.scl(delta));
        velocity.scl(1/delta);

        if (position.x + width < 0){
            isScrollLeft = true;
        }
    }

    public void stop(){
        velocity.x = 0;
    }

    public void reset(float newX){
        position.x = newX;
        isScrollLeft = false;
    }

    public boolean isScrollLeft(){
        return isScrollLeft;
    }

    public float getTailX(){
        return position.x + width;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
}
