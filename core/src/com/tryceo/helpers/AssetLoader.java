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

    public static TextureRegion pipeTopUp, pipeTopDown, pipe;

    public static TextureRegion gameName, gamePlayButtonNormal, gamePlayButtonTouched;

    public static BitmapFont font, fontShadow;

    public static void load() {
        texture = new Texture(Gdx.files.internal("texture.png"));

        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        grass = new TextureRegion(texture, 91, 43, Grass.GRASS_WIDTH, Grass.GRASS_HEIGHT);
        grass.flip(false, true);

        background = new TextureRegion(texture, 120, 54, 136, 74);
        background.flip(false, true);

        coffeeDown = new TextureRegion(texture, 136, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeDown.flip(true, true);
        coffeeMid = new TextureRegion(texture, 153, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeMid.flip(true, true);
        coffeeUp = new TextureRegion(texture, 170, 0, Coffee.COFFEE_WIDTH, Coffee.COFFEE_HEIGHT);
        coffeeUp.flip(true, true);

        TextureRegion[] coffees = {coffeeDown, coffeeMid, coffeeUp};
        coffeeAnimation = new Animation(Coffee.COFFEE_ANIMATION_SPEED, coffees);
        coffeeAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        pipeTopUp = new TextureRegion(texture, 192, 0, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);
        pipeTopDown = new TextureRegion(pipeTopUp);
        pipeTopDown.flip(false, true);

        pipe = new TextureRegion(texture, 136, 16, Pipe.PIPE_WIDTH, Pipe.PIPE_HEIGHT);
        pipe.flip(false, true);

        gameName = new TextureRegion(texture, 0, 55, 117, 34);
        gameName.flip(false, true);

        gamePlayButtonNormal = new TextureRegion(texture, 0, 90, 29, 16 );
        gamePlayButtonNormal.flip(false, true);
        gamePlayButtonTouched = new TextureRegion(texture, 29, 90, 29, 16);
        gamePlayButtonTouched.flip(false,true);


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
