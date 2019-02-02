package gamefromscratch.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AISprite extends Sprite {
    private Vector2 velocity = new Vector2();
    private float speed = 900.0F;
    private float tolerance = 3.0F;
    private Array<Vector2> path;
    private int waypoint = 0;

    public AISprite(Sprite sprite, Array<Vector2> path) {
        super(sprite);
        this.path = path;
    }

    public void draw(SpriteBatch spriteBatch) {
        this.update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        float angle = (float)Math.atan2((double)(((Vector2)this.path.get(this.waypoint)).y - this.getY()), (double)(((Vector2)this.path.get(this.waypoint)).x - this.getX()));
        this.velocity.set((float)Math.cos((double)angle) * this.speed, (float)Math.sin((double)angle) * this.speed);
        this.setPosition(this.getX() + this.velocity.x * delta, this.getY() + this.velocity.y * delta);
        this.setRotation(angle * 57.295776F);
        if (this.isWaypointReached()) {
            this.setPosition(((Vector2)this.path.get(this.waypoint)).x, ((Vector2)this.path.get(this.waypoint)).y);
            if (this.waypoint + 1 >= this.path.size) {
                this.waypoint = 0;
            } else {
                ++this.waypoint;
            }
        }

    }

    public boolean isWaypointReached() {
        return ((Vector2)this.path.get(this.waypoint)).x - this.getX() <= this.speed / this.tolerance * Gdx.graphics.getDeltaTime() && ((Vector2)this.path.get(this.waypoint)).y - this.getY() <= this.speed / this.tolerance * Gdx.graphics.getDeltaTime();
    }

    public Array<Vector2> getPath() {
        return this.path;
    }

    public int getWaypoint() {
        return this.waypoint;
    }
}
