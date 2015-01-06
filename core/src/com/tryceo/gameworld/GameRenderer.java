package com.tryceo.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tryceo.gameobjects.Coffee;
import com.tryceo.gameobjects.Grass;
import com.tryceo.gameobjects.Pipe;
import com.tryceo.gameobjects.ScrollableHandler;
import com.tryceo.helpers.AssetLoader;

/**
 * Class for rendering the game
 */
public class GameRenderer {

    public static TextureRegion background, grass;
    public static Animation coffeeAnimation;
    public static TextureRegion coffeeMid, coffeeDown, coffeeUp;
    public static TextureRegion pipeUp, pipeDown, bar;
    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Coffee coffee;
    private ScrollableHandler handler;
    private Grass grass1, grass2;
    private Pipe pipe1, pipe2, pipe3;
    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int midPointY, int gameHeight) {
        this.world = world;
        this.midPointY = midPointY;
        this.gameHeight = gameHeight;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 137, 204);

        //Get all the objects
        coffee = world.getCoffee();
        handler = world.getHandler();
        grass1 = handler.getGrass1();
        grass2 = handler.getGrass2();
        pipe1 = handler.getPipe1();
        pipe2 = handler.getPipe2();
        pipe3 = handler.getPipe3();

        //Get all the assets
        background = AssetLoader.background;
        grass = AssetLoader.grass;
        coffeeAnimation = AssetLoader.coffeeAnimation;

        coffeeMid = AssetLoader.coffeeMid;
        coffeeDown = AssetLoader.coffeeDown;
        coffeeUp = AssetLoader.coffeeUp;

        pipeUp = AssetLoader.pipeUp;
        pipeDown = AssetLoader.pipeDown;
        bar = AssetLoader.bar;

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }


    public void drawGrass() {
        batch.draw(grass, grass1.getX(), grass1.getY(), grass1.getWidth(), grass1.getHeight());
        batch.draw(grass, grass2.getX(), grass2.getY(), grass2.getWidth(), grass2.getHeight());
    }

    private void drawPipeTops() {

        batch.draw(pipeUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batch.draw(pipeDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + Pipe.PIPE_VERTICAL_GAP, 24, 14);

        batch.draw(pipeUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batch.draw(pipeDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + Pipe.PIPE_VERTICAL_GAP, 24, 14);

        batch.draw(pipeUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batch.draw(pipeDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + Pipe.PIPE_VERTICAL_GAP, 24, 14);
    }

    private void drawPipes() {

        batch.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batch.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + Pipe.PIPE_VERTICAL_GAP,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + Pipe.PIPE_VERTICAL_GAP));

        batch.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batch.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + Pipe.PIPE_VERTICAL_GAP,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + Pipe.PIPE_VERTICAL_GAP));

        batch.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batch.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + Pipe.PIPE_VERTICAL_GAP,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + Pipe.PIPE_VERTICAL_GAP));
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);


        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);


        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batch.begin();
        batch.disableBlending();
        batch.draw(background, 0, midPointY + 23, 136, 43);


        drawPipes();
        drawGrass();

        batch.enableBlending();

        drawPipeTops();

        if (coffee.shouldNotFlap()) {
            batch.draw(coffeeMid, coffee.getX(), coffee.getY(), coffee.getWidth() / 2.0f, coffee.getHeight() / 2.0f, coffee.getWidth(), coffee.getHeight(), 1, 1, coffee.getRotation());

        } else {
            batch.draw(coffeeAnimation.getKeyFrame(runTime), coffee.getX(), coffee.getY(), coffee.getWidth() / 2.0f, coffee.getHeight() / 2.0f, coffee.getWidth(), coffee.getHeight(), 1, 1, coffee.getRotation());
        }


        String score = world.getScore() + "";


        AssetLoader.fontShadow.draw(batch, "" + world.getScore(), (136 / 2) - (3 * score.length()), 12);

        AssetLoader.font.draw(batch, "" + world.getScore(), (136 / 2) - (3 * score.length() - 1), 11);
        batch.end();


    }
}
