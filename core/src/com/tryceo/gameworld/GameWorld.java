package com.tryceo.gameworld;

import com.tryceo.gameobjects.Coffee;


/**
 * Created by Jack on 12/29/2014.
 */
public class GameWorld {

    private Coffee coffee;

    public GameWorld(int midPointY){
        coffee = new Coffee(33,midPointY-5,17,12);
    }

    public void update(float delta){
        coffee.update(delta);
    }

    public Coffee getCoffee(){
        return coffee;
    }
}
