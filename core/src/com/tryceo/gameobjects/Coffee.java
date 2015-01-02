package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jack on 12/29/2014.
 */
public class Coffee {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    public Coffee(float x, float y, int width, int height){
        position = new Vector2(x,y);

        velocity = new Vector2(0,0);

        acceleration = new Vector2(0, 460);

        this.width = width;
        this.height = height;
    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));//scale velocity to frame rate

        if (velocity.y > 200){
            velocity.y = 200;//terminal velocity
        }

        position.add(velocity.cpy().scl(delta));//scale position to frame rate

        if (velocity.y < 0){
            rotation -= 600 * delta;

            if (rotation < -20){
                rotation = -20;
            }
        }

        if (isFalling()){
            rotation+= 480 * delta;
            if (rotation > 90){
                rotation = 90;
            }
        }
    }

    public boolean isFalling(){
        return velocity.y > 110;
    }

    public boolean shouldntFlap(){
        return velocity.y > 70;
    }

    public void onClick(){
        velocity.y = -140;
    }
    public float getRotation() {
        return rotation;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX(){
        return position.x;
    }
    public float getY(){
        return position.y;
    }

    public Vector2 getPosition() {
        return position;

    }
}
