package com.tryceo.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by Jack on 12/29/2014.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion background, grass;

    public static Animation coffeeAnimation;

    public static TextureRegion coffee, coffeeDown, coffeeUp;

    public static TextureRegion pipeUp, pipeDown, bar;

    public static void load() {
        texture = new Texture(Gdx.files.internal("texture.png"));

        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new TextureRegion(texture, 0, 0, 136, 42);
        background.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        coffeeDown = new TextureRegion(texture, 136, 0, 17, 12);
        coffeeDown.flip(true, true);
        coffee = new TextureRegion(texture, 153, 0, 17, 12);
        coffee.flip(true, true);
        coffeeUp = new TextureRegion(texture, 170, 0, 17, 12);
        coffeeUp.flip(true, true);

        TextureRegion[] coffees = {coffeeDown, coffee, coffeeUp};
        coffeeAnimation = new Animation(0.06f, coffees);
        coffeeAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipeUp = new TextureRegion(texture, 192, 0, 24, 14);
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);


    }

    public static void dispose() {
        texture.dispose();
    }
}
