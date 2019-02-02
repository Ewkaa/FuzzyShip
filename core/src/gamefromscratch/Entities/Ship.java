package gamefromscratch.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

import static gamefromscratch.Fuzzy.DEFAULT_HEIGHT;
import static gamefromscratch.Fuzzy.DEFAULT_WIDTH;

public class Ship extends Image {

    private static float Speed;
    private static Logic logic;
    public boolean Moving;
    public static final int WIDTH = 25;
    public static final int HEIGHT = 50;
    private float STARTING_X = 1000;
    private float STARTING_Y = 650;
    private float SensorAngle = 8; //kąt odchylenia sensorów


    private Sprite sensor1, sensor2, sensor3;


    public Ship() {
        super(new Texture("ship.jpg"));
        this.setSize(WIDTH, HEIGHT);
        this.setOrigin(this.getWidth() / 2.0f, 0);

        this.setPosition(STARTING_X, STARTING_Y);
        this.setSpeed(5.0F);
        this.Moving = false;
        this.setDebug(true);
        this.logic = new Logic();

        initSensors();
        logic.distanceFuzzy(0, 75,150,250,250, 500);
        logic.angleFuzzy();
    }
    //inicjalizacja sensorów (
    private void initSensors() {
        sensor1 = new Sprite(new Texture("GreenPix.png"));
        sensor1.setPosition(this.getX() + (this.getWidth() / 2), this.getY());
        sensor1.setSize(1, 1);
        sensor1.setColor(255, 0, 0, 255);
        sensor1.setRotation(SensorAngle * (-1) + 180);


        sensor2 = new Sprite(new Texture("GreenPix.png"));
        sensor2.setPosition(this.getX() + (this.getWidth() / 2), this.getY());
        sensor2.setSize(1, 1);
        sensor2.setColor(0, 255, 0, 255);
        sensor2.setRotation(180);


        sensor3 = new Sprite(new Texture("GreenPix.png"));
        sensor3.setPosition(this.getX() + (this.getWidth() / 2), this.getY());
        sensor3.setSize(1, 1);
        sensor3.setColor(0, 0, 255, 1);
        sensor3.setRotation(180 + SensorAngle);
    }

    public static void setSpeed(float speed) {
        if(speed <= 0.0f) speed = 0.1f;
        Speed = speed;
    }

    public float getSpeed() {
        return Speed;
    }

    public void Move(ArrayList<Obstacle> obstacles) {
        //float blur = logic.Blurring(Angle, (int)position);
        //float rotateBy = 0;//Math.round(blur);
        // double rotateByNew=Math.round(rotation+(Math.toDegrees(Math.asin((2*Math.sin(Math.toRadians(rotateBy))/50)*Speed))));
        /*float blur = logic.getLogic((int)sensor1.getHeight(),(int)sensor2.getHeight(),(int)sensor3.getHeight());
        float rotateBy = Angle + Math.round(blur);
        if (rotateBy >= 360) rotateBy = rotateBy - 360;
        else if (rotateBy < 0) rotateBy = 360 - rotateBy;*/

        float rotation =  this.getRotation();
        float position = this.getX()-(Ship.WIDTH/2);
        float Angle = getAngle();

        //float blur = 0; //logic.Blurring(Angle, (int)position);
       // logic.distanceFuzzy(0, 150, 220, 320, 320, 401);

        float blur = logic.getLogic((int)sensor3.getHeight(),(int)sensor1.getHeight(),(int)sensor2.getHeight());

        float rotateBy = Math.round(blur);
        double rotateByNew=Math.round(rotation+(Math.toDegrees(Math.asin((2*Math.sin(Math.toRadians(rotateBy))/30)*Speed))));

        setRotation((float)rotateByNew, obstacles);
        double xx=(this.getX()+Speed*(Math.sin(Math.toRadians(rotateBy+(-Angle))-Math.sin(Math.toRadians(rotateBy))*Math.cos(Math.toRadians((-Angle))))));
        this.setX((float)xx);
        double yy=(this.getY()-Speed*(Math.cos(Math.toRadians(rotateBy+(-Angle))-Math.sin(Math.toRadians(rotateBy))*Math.cos(Math.toRadians((-Angle))))));
        this.setY((float)yy);




        sensor1.setX((float) xx + getWidth() / 2);
        sensor1.setY((float) yy);
        sensor2.setX((float) xx + getWidth() / 2);
        sensor2.setY((float) yy);
        sensor3.setX((float) xx + getWidth() / 2);
        sensor3.setY((float) yy);


      //  if (this.getX() <= 100){
       //     this.setMoving(false);
       // }



        if (this.getX() <= 150){
            this.setMoving(false);
        }

    }

    public float getAngle() {
        float Angle = this.getRotation() * (-1);

        if (Angle >= 360) Angle -= ((int) Angle / 360) * 360;
        if (Angle <= -360) Angle += ((int) Angle / 360) * 360;
        if (Angle >= 180) Angle -= 360;
        if (Angle < -180) Angle += 360;

        return Angle;
    }

    public void changePosition(float x, float y) {
        if (!this.isMoving()) {
            this.setPosition(x, y);
        }

    }

    public boolean isMoving() {
        return this.Moving;
    }

    public void setMoving(boolean moving) {
        Moving = moving;
    }

    public Sprite getSensor1() {
        return sensor1;
    }

    public Sprite getSensor2() {
        return sensor2;
    }

    public Sprite getSensor3() {
        return sensor3;
    }


//długosc sensora
    public void setSensorsSize(ArrayList<Obstacle> obstacles) {
        sensor1.setSize(1, 1);
        sensor2.setSize(1, 1);
        sensor3.setSize(1, 1);

        for (int a = 0; a < 100; a++) {
            boolean shouldGrown = false;
            float x = sensor1.getVertices()[SpriteBatch.X2];
            float y = sensor1.getVertices()[SpriteBatch.Y2];


            if (x <= DEFAULT_WIDTH && y <= DEFAULT_HEIGHT) {
                if (x >= 0 && y >= 0) {
                    shouldGrown = true;
                }
            }

            if (obstacles != null && shouldGrown == true) {
                for (Obstacle obc :
                        obstacles) {
                    if (obc.isCollisionByPoint(x, y)) {
                        shouldGrown = false;
                                          }
                }
            }

            if (shouldGrown) {
                sensor1.setSize(1, sensor1.getHeight() + 5);
            }


            x = sensor2.getVertices()[SpriteBatch.X2];
            y = sensor2.getVertices()[SpriteBatch.Y2];

            boolean shouldGrown2 = false;

            if (x <= DEFAULT_WIDTH && y <= DEFAULT_HEIGHT) {
                if (x >= 0 && y >= 0) {
                    shouldGrown2 = true;
                }
            }

            if (obstacles != null && shouldGrown2 == true) {
                for (Obstacle obc :
                        obstacles) {
                    if (obc.isCollisionByPoint(x, y)) {
                        shouldGrown2 = false;
                       }
                }
            }


            if (shouldGrown2) {
                    sensor2.setSize(1, sensor2.getHeight() + 5);
            }
            //--------------------------------------------------------------------
            x = sensor3.getVertices()[SpriteBatch.X2];
            y = sensor3.getVertices()[SpriteBatch.Y2];


            boolean shouldGrown3 = false;

            if (x <= DEFAULT_WIDTH && y <= DEFAULT_HEIGHT) {
                if (x >= 0 && y >= 0) {
                    shouldGrown3 = true;
                }
            }

            if (obstacles != null && shouldGrown3 == true) {
                for (Obstacle obc :
                        obstacles) {
                    if (obc.isCollisionByPoint(x, y)) {
                        shouldGrown3 = false;

                    }
                }


                if (shouldGrown3) {
                        sensor3.setSize(1, sensor3.getHeight() + 5);
                }

                if (shouldGrown == false && shouldGrown2 == false && shouldGrown3 == false) break;

            }
      /*

        //--------------------------------------------------------------------
       */

        }}

        public void setRotation ( float degrees, ArrayList< Obstacle > obstacles){
            super.setRotation(degrees);
            sensor1.setRotation(degrees - SensorAngle + 180);
            sensor2.setRotation(degrees + 180);
            sensor3.setRotation(degrees + SensorAngle + 180);

            setSensorsSize(obstacles);


        }

    }
