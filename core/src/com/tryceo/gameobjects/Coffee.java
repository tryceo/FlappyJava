package com.tryceo.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * The Coffee actor class
 */
public class Coffee {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    public static final int COFFEE_WIDTH = 17;
    public static final int COFFEE_HEIGHT = 12;

    private Circle circle;

    public Circle getCircle() {
        return circle;
    }

    public Coffee(float x, float y, int width, int height){
        position = new Vector2(x,y);

        velocity = new Vector2(0,0);

        acceleration = new Vector2(0, 460);

        this.width = width;
        this.height = height;

        circle = new Circle();

    }


    public void update(float delta){
        velocity.add(acceleration.scl(delta));//scale velocity to frame rate
        acceleration.scl(1/delta);


        if (velocity.y > 200){
            velocity.y = 200;//terminal velocity
        }

        position.add(velocity.scl(delta));//scale position to frame rate
        velocity.scl(1/delta);

        circle.set(position.x + 9, position.y + 6, 6.5f);//center at 9, 6, with a radius of 6.5

        if (velocity.y < 0){
            rotation -= 600 * delta;//counterclockwise

            if (rotation < -20){
                rotation = -20;
            }
        }

        if (isFalling()){
            rotation+= 480 * delta;//clockwise
            if (rotation > 90){
                rotation = 90;
            }
        }
    }

    public void stop(){

    }
    public boolean isFalling(){
        return velocity.y > 110;
    }

    public boolean shouldNotFlap(){
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
