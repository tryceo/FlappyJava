package com.tryceo.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Jack on 12/29/2014.
 */
public class GameWorld {

    private Rectangle rectangle;

    public GameWorld (){
        rectangle = new Rectangle(0,100,17,12);
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void update (float delta){
        rectangle.x++;

        if (rectangle.getX() > 137f){
            rectangle.setX(0f);

        }
        Gdx.app.log("GameWorld", "update");
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
