package com.tryceo.gameworld;

import com.tryceo.gameobjects.Coffee;
import com.tryceo.gameobjects.ScrollableHandler;


/**
 * Class for updating the game world
 */
public class GameWorld {

    private Coffee coffee;
    ScrollableHandler handler;

    public GameWorld(int midPointY){
        coffee = new Coffee(33,midPointY-5,17,12);
        handler = new ScrollableHandler(midPointY+66);
    }

    public void update(float delta){
        coffee.update(delta);
        handler.update(delta);

        if (handler.collide(coffee)){
            handler.stop();
            coffee.stop();
        }
    }

    public Coffee getCoffee(){
        return coffee;
    }

    public ScrollableHandler getHandler() {
        return handler;
    }
}
