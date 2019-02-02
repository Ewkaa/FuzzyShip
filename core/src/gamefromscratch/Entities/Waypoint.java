package gamefromscratch.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public class Waypoint{
    private ShapeRenderer sr;
    private SpriteBatch batch;
    private Array<AISprite> aiSprites;
    private Sprite sprite;

    public Waypoint() {
    }

    public void render(float delta) {
        Iterator var2 = this.aiSprites.iterator();

        while(var2.hasNext()) {
            AISprite aiSprite = (AISprite)var2.next();
            this.sr.setColor(Color.CYAN);
            this.sr.begin(ShapeType.Line);
            this.sr.line(new Vector2(aiSprite.getX(), aiSprite.getY()), (Vector2)aiSprite.getPath().get(aiSprite.getWaypoint()));
            this.sr.end();
            Vector2 previous = (Vector2)aiSprite.getPath().first();

            Vector2 waypoint;
            for(Iterator var5 = aiSprite.getPath().iterator(); var5.hasNext(); previous = waypoint) {
                waypoint = (Vector2)var5.next();
                this.sr.setColor(Color.WHITE);
                this.sr.begin(ShapeType.Line);
                this.sr.line(previous, waypoint);
                this.sr.end();
                this.sr.begin(ShapeType.Filled);
                this.sr.circle(waypoint.x, waypoint.y, 5.0F);
                this.sr.end();
            }
        }

    }

    public void show() {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        this.sr = new ShapeRenderer();
        this.batch = new SpriteBatch();
        this.sprite = new Sprite(new Texture("ship.jpg"));
        this.sprite.setSize(50.0F, 50.0F);
        this.sprite.setOrigin(0.0F, 0.0F);
        this.aiSprites = new Array();

        for(int i = 0; i < MathUtils.random(5, 10); ++i) {
            AISprite aiSprite = new AISprite(this.sprite, this.getRandomPath());
            aiSprite.setPosition((float)MathUtils.random(0, Gdx.graphics.getWidth()), (float)MathUtils.random(0, Gdx.graphics.getHeight()));
            this.aiSprites.add(aiSprite);
        }

    }

    private Array<Vector2> getRandomPath() {
        Array<Vector2> path = new Array();

        for(int i = 0; i < MathUtils.random(5, 10); ++i) {
            path.add(new Vector2((float)MathUtils.random(0, Gdx.graphics.getWidth()), (float)MathUtils.random(0, Gdx.graphics.getHeight())));
        }

        return path;
    }
}
