package com.tryceo.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Jack on 12/29/2014.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld world){
        this.world = world;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 136, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void render(){
        Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0,0,0,1); //black background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.GRAY);

        shapeRenderer.rect(world.getRectangle().getX(), world.getRectangle().getY(), world.getRectangle().getWidth(), world.getRectangle().getHeight());

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(Color.RED);

        shapeRenderer.rect(world.getRectangle().getX(), world.getRectangle().getY(), world.getRectangle().getWidth(), world.getRectangle().getHeight());

        shapeRenderer.end();
    }
}
