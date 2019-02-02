package gamefromscratch.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import gamefromscratch.Fuzzy;
import gamefromscratch.Entities.Obstacle;
import gamefromscratch.Entities.Ship;

import java.util.ArrayList;

import static gamefromscratch.Fuzzy.DEFAULT_HEIGHT;

public class MainScreen extends AbstractScreen {
    private Image background;
    private Ship ship;

   // private Obstacle obstacles[];
    private ArrayList<Obstacle> obstacles;
    private float time = 0.0F;
    private Skin skin;

    private Slider xPosSlider;
    private Slider yPosSlider;
    private Slider AngleSlider;

    private boolean AddingMode = false;

    private Label ModeLabel;

    public MainScreen(Fuzzy game) {
        super(game);
        this.init();
    }

    private void init() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        initBackground();
        initShip();
        initObstacles();
        initButtons();
        initSliders(10.0F, 50.0F, 300.0F);


    }

    private void initButtons() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();

        TextButton buttonStartMove = new TextButton("Przycisk", skin);
        buttonStartMove.setWidth(100);
        buttonStartMove.setHeight(30);
        buttonStartMove.setX(10);
        buttonStartMove.setY(500);

        buttonStartMove.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                ship.setMoving(!ship.isMoving());
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonStartMove);

        ModeLabel = new Label("", skin);
        ModeLabel.setPosition(50, 850);
        TextButton buttonCreating = new TextButton("Zmien tryb", skin);
        buttonCreating.setWidth(100);
        buttonCreating.setHeight(30);
        buttonCreating.setX(10);
        buttonCreating.setY(550);

        if (AddingMode) {
            ModeLabel.setText("Tryb: Dodawanie");
        } else {
            ModeLabel.setText("Tryb: Plywanie");
        }

        buttonCreating.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                AddingMode = !AddingMode;

                if (AddingMode) {
                    ModeLabel.setText("Tryb: Dodawanie");
                } else {
                    ModeLabel.setText("Tryb: Plywanie");
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(buttonCreating);
        stage.addActor(ModeLabel);




        TextButton txtTest = new TextButton("txtTest", skin);
        txtTest.setWidth(100);
        txtTest.setHeight(30);
        txtTest.setX(10);
        txtTest.setY(450);

        txtTest.addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                ship.setSensorsSize(obstacles);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(txtTest);

    }

    private void initSliders(final float xPos, float yPos, float width) {

        final Label xPosSliderLabel = new Label("Os X", skin);
        xPosSliderLabel.setPosition(xPos,yPos);

        xPosSlider = new Slider(0, 900+Ship.WIDTH, 1, false, skin);
        xPosSlider.setWidth(width);
        xPosSlider.setValue(ship.getX()-300);
        xPosSlider.setPosition(xPosSliderLabel.getX()+xPosSliderLabel.getWidth()+5, xPosSliderLabel.getY());

        stage.addActor(xPosSlider);
        stage.addActor(xPosSliderLabel);

        xPosSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                ship.changePosition(xPosSlider.getValue(), yPosSlider.getValue());
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        yPos += 30;

        Label yPosSliderLabel = new Label("Os Y", skin);
        yPosSliderLabel.setPosition(xPos,yPos);

        yPosSlider = new Slider(0, 450-ship.HEIGHT, 10, false, skin);
        yPosSlider.setWidth(width);
        yPosSlider.setValue(ship.getY()-300);
        yPosSlider.setPosition(yPosSliderLabel.getX()+yPosSliderLabel.getWidth()+5, yPosSliderLabel.getY());


        stage.addActor(yPosSlider);
        stage.addActor(yPosSliderLabel);

        yPosSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                ship.changePosition(xPosSlider.getValue(), yPosSlider.getValue());
            }
        });

        //--------------------------------------------------------------------------------------------------------------
        yPos += 30;

        Label AngleSliderLabel = new Label("Kat ", skin);
        AngleSliderLabel.setPosition(xPos,yPos);

        AngleSlider = new Slider(-180, 180, 1, false, skin);
        AngleSlider.setWidth(800);
        AngleSlider.setPosition(AngleSliderLabel.getX()+AngleSliderLabel.getWidth()+5, AngleSliderLabel.getY());

        stage.addActor(AngleSlider);
        stage.addActor(AngleSliderLabel);

        AngleSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                ship.setRotation(AngleSlider.getValue(), obstacles);

            }
        });
    }

    private void initShip() {
        ship = new Ship();
        stage.addActor(ship);
    }

    private void initBackground() {
        background = new Image(new Texture("background.jpg"));
        background.setSize(1600.0F, 900.0F);
        background.setPosition(0.0F, 0.0F);
        stage.addActor(background);
    }

    private void initObstacles() {
        obstacles = new ArrayList<Obstacle>();    }


        public void render(float delta) {
        super.render(delta);
        this.update();
         this.stage.draw();

        this.batch.begin();


         ship.getSensor1().draw(batch);
         ship.getSensor2().draw(batch);
         ship.getSensor3().draw(batch);
         this.batch.end();



        if (ship.isMoving()) {
            time += delta;
            if (time >= 0.15f) {
                time = 0.0f;

                ship.Move(obstacles);
            }
        }


    }

    private void update() {
        this.stage.act();

        if(AddingMode) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                Obstacle obstacle = new Obstacle(Gdx.input.getX() - (Obstacle.WIDTH / 2), DEFAULT_HEIGHT - Gdx.input.getY() - (Obstacle.HEIGHT / 2));
                obstacles.add(obstacle);
                stage.addActor(obstacle);
            }
        }
    }

}
