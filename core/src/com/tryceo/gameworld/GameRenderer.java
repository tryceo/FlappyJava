package com.tryceo.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tryceo.gameobjects.Coffee;
import com.tryceo.helpers.AssetLoader;

/**
 * Created by Jack on 12/29/2014.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Coffee coffee;
    private int midPointY;
    private int gameHeight;
    public GameRenderer(GameWorld world, int midPointY, int gameHeight){
        this.world = world;
        coffee = world.getCoffee();
        this.midPointY = midPointY;
        this.gameHeight = gameHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 137, 204);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(float runTime){


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        // End ShapeRenderer
        shapeRenderer.end();

        // Begin SpriteBatch
        batch.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        batch.disableBlending();
        batch.draw(AssetLoader.background, 0, midPointY + 23, 136, 43);
        batch.draw(AssetLoader.pipeUp, 50, midPointY, 24, 14);

        // The bird needs transparency, so we enable that again.
        batch.enableBlending();

        // Draw bird at its coordinates. Retrieve the Animation object from
        // AssetLoader
        // Pass in the runTime variable to get the current frame.
        batch.draw(AssetLoader.coffeeAnimation.getKeyFrame(runTime),
                coffee.getX(), coffee.getY(), coffee.getWidth(), coffee.getHeight());

        // End SpriteBatch
        batch.end();
    }
}
