package gamefromscratch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import gamefromscratch.Fuzzy;


import static gamefromscratch.Fuzzy.DEFAULT_HEIGHT;
import static gamefromscratch.Fuzzy.DEFAULT_WIDTH;

public class AbstractScreen implements Screen {
    protected Fuzzy game;
    protected Stage stage;
    private OrthographicCamera camera;
    protected SpriteBatch batch;

    public AbstractScreen(Fuzzy game)
    {
        this.game = game;
        CreateCamera();
        stage = new Stage(new StretchViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
    }

    private void CreateCamera()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        camera.update();
    }


    protected void clearScreen()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {    }

    @Override
    public void render(float delta)
    {
        clearScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height){}

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose()
    {
        game.dispose();
    }

}
