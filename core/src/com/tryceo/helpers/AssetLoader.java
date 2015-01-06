package com.tryceo.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tryceo.gameobjects.Coffee;
import com.tryceo.gameobjects.Grass;
import com.tryceo.gameobjects.Pipe;


/**
 * Class for loading texture assets
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion background, grass;

    public static Animation coffeeAnimation;

    public static TextureRegion coffeeMid, coffeeDown, coffeeUp;

    public static TextureRegion pipeUp, pipeDown, bar;

    public static BitmapFont font, fontShadow;

    public static void load() {
        texture = new Texture(Gdx.files.internal("texture.png"));

        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        background = new TextureRegion(texture, 0, 0, 136, 42);
        background.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT);
        grass.flip(false, true);

        coffeeDown = new TextureRegion(texture, 136, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeDown.flip(true, true);
        coffeeMid = new TextureRegion(texture, 153, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeMid.flip(true, true);
        coffeeUp = new TextureRegion(texture, 170, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeUp.flip(true, true);

        TextureRegion[] coffees = {coffeeDown, coffeeMid, coffeeUp};
        coffeeAnimation = new Animation(0.06f, coffees);
        coffeeAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipeUp = new TextureRegion(texture, 192, 0, Pipe.PIPE_TOP_WIDTH, 14);
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, Pipe.PIPE_WIDTH, 3);
        bar.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(.25f, -.25f);
        fontShadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        fontShadow.setScale(.25f, -.25f);

    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
        fontShadow.dispose();
    }
}
