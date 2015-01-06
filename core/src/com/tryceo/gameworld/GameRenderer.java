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
    public static TextureRegion pipeTopUp, pipeTopDown, pipe;
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

        pipeTopUp = AssetLoader.pipeTopUp;
        pipeTopDown = AssetLoader.pipeTopDown;
        pipe = AssetLoader.pipe;

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

        batch.draw(pipeTopUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - Pipe.PIPE_TOP_HEIGHT, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);
        batch.draw(pipeTopDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + Pipe.PIPE_VERTICAL_GAP, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);

        batch.draw(pipeTopUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - Pipe.PIPE_TOP_HEIGHT, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);
        batch.draw(pipeTopDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + Pipe.PIPE_VERTICAL_GAP, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);

        batch.draw(pipeTopUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - Pipe.PIPE_TOP_HEIGHT, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);
        batch.draw(pipeTopDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + Pipe.PIPE_VERTICAL_GAP, Pipe.PIPE_TOP_WIDTH, Pipe.PIPE_TOP_HEIGHT);
    }

    private void drawPipes() {

        drawTiledPipe(pipe1);
        drawTiledPipe(pipe2);
        drawTiledPipe(pipe3);
    }

    private void drawTiledPipe(Pipe p) {

        float i = p.getY();

        //Draw top pipe
        while (i < p.getHeight() - Pipe.PIPE_HEIGHT) {
            batch.draw(pipe, p.getX(), i);
            i += Pipe.PIPE_HEIGHT;
        }
        batch.draw(pipe, p.getX(), i, p.getWidth(), p.getHeight() - i);

        i = p.getY() + p.getHeight() + Pipe.PIPE_VERTICAL_GAP;

        //Draw bottom pipe
        while (i < midPointY + 66 - Pipe.PIPE_HEIGHT) {
            batch.draw(pipe, p.getX(), i);
            i += Pipe.PIPE_HEIGHT;
        }
        batch.draw(pipe, p.getX(), i, p.getWidth(), midPointY + 66 - i);

    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Sky
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, GameWorld.groundPosY);

        //Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, GameWorld.groundPosY + Grass.GRASS_HEIGHT, 136, 52);

        shapeRenderer.end();

        batch.begin();
        batch.disableBlending();

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
