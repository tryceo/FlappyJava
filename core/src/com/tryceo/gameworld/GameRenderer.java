package com.tryceo.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    public static TextureRegion background, grass;
    public static Animation coffeeAnimation;
    public static TextureRegion coffeeMid, coffeeDown, coffeeUp;
    public static TextureRegion pipeUp, pipeDown, bar;

    public GameRenderer(GameWorld world, int midPointY, int gameHeight){
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



    public void drawGrass(){
        batch.draw(grass, grass1.getX(), grass1.getY(), grass1.getWidth(), grass1.getHeight());
        batch.draw(grass, grass2.getX(), grass2.getY(), grass2.getWidth(), grass2.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.

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
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
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

    public void render(float runTime){
        Gdx.gl.glClearColor(0,0,0,1);
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
        batch.draw(background, 0, midPointY +23, 136, 43);

        drawGrass();
        drawPipes();

        batch.enableBlending();

        drawSkulls();

        if(coffee.shouldNotFlap()){
            batch.draw(coffeeMid, coffee.getX(), coffee.getY(), coffee.getWidth() /2.0f, coffee.getHeight()/2.0f, coffee.getWidth(), coffee.getHeight(), 1,1, coffee.getRotation());

        }else{
            batch.draw(coffeeAnimation.getKeyFrame(runTime), coffee.getX(), coffee.getY(), coffee.getWidth() /2.0f, coffee.getHeight()/2.0f, coffee.getWidth(), coffee.getHeight(), 1,1, coffee.getRotation());
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.RED);

        shapeRenderer.circle(coffee.getCircle().x,
                coffee.getCircle().y, coffee.getCircle().radius);

        shapeRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y,
                pipe1.getBarUp().width, pipe1.getBarUp().height);
        shapeRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y,
                pipe2.getBarUp().width, pipe2.getBarUp().height);
        shapeRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y,
                pipe3.getBarUp().width, pipe3.getBarUp().height);

        // Bar down for pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y,
                pipe1.getBarDown().width, pipe1.getBarDown().height);
        shapeRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y,
                pipe2.getBarDown().width, pipe2.getBarDown().height);
        shapeRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y,
                pipe3.getBarDown().width, pipe3.getBarDown().height);

        // Skull up for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getPipeUp().x, pipe1.getPipeUp().y,
                pipe1.getPipeUp().width, pipe1.getPipeUp().height);
        shapeRenderer.rect(pipe2.getPipeUp().x, pipe2.getPipeUp().y,
                pipe2.getPipeUp().width, pipe2.getPipeUp().height);
        shapeRenderer.rect(pipe3.getPipeUp().x, pipe3.getPipeUp().y,
                pipe3.getPipeUp().width, pipe3.getPipeUp().height);

        // Skull down for Pipes 1 2 and 3
        shapeRenderer.rect(pipe1.getPipeDown().x, pipe1.getPipeDown().y,
                pipe1.getPipeDown().width, pipe1.getPipeDown().height);
        shapeRenderer.rect(pipe2.getPipeDown().x, pipe2.getPipeDown().y,
                pipe2.getPipeDown().width, pipe2.getPipeDown().height);
        shapeRenderer.rect(pipe3.getPipeDown().x, pipe3.getPipeDown().y,
                pipe3.getPipeDown().width, pipe3.getPipeDown().height);
        shapeRenderer.end();

    }
}
