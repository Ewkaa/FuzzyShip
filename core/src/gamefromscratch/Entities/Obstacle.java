package gamefromscratch.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Obstacle extends Image {

    public final static float WIDTH = 20;
    public final static float HEIGHT = 30;

    public Obstacle(float x, float y) {
        super(new Texture("boja.png"));
        this.setSize(Obstacle.WIDTH, Obstacle.HEIGHT);
        this.setOrigin(this.getWidth()/2.0f, this.getHeight()/2.0f);

        this.setPosition(x, y);
        this.setDebug(true);
    }

    public boolean isCollisionByPoint(float x, float y)
    {
        if(x >= this.getX() && y >= this.getY() && x <= this.getX()+this.getWidth() && y <= this.getY()+this.getHeight())
        {
            return true;
        }
        return false;
    }
}