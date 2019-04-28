package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A class modelling a drawable object on screen.
 */
public class DrawableActor extends Actor {

    private final TextureRegion texture;

    /**
     * Create a drawable object.
     *
     * @param texture the texture to draw for the enemy.
     * @param x       the x coordinate in world space of the object.
     * @param y       the y coordinate in world space of the object.
     */
    DrawableActor(TextureRegion texture, int x, int y) {
        this.texture = texture;
        setX(x);
        setY(y);

        // Needed for collision detection
        setWidth(1);
        setHeight(1);
    }

    /**
     * Create a drawable object at (0,0) in world space.
     *
     * @param texture the texture to draw for the enemy.
     */
    protected DrawableActor(TextureRegion texture) {
        this(texture, 0, 0);
    }

    /**
     * @return the texture of the object.
     */
    public TextureRegion getTexture() {
        return texture;
    }

    /**
     * @param a the transparency value of the object, between 0 and 1.
     */
    protected void setAlpha(float a) {
        setColor(1, 1, 1, a);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 originCoords = originLocalToScreenCoordinates();
        Vector2 endCoords = endLocalToScreenCoordinates();
        batch.setColor(getColor());
        batch.draw(texture, originCoords.x, originCoords.y, endCoords.x - originCoords.x, endCoords.y - originCoords.y);
    }

    /**
     * @return the screen space coordinates of (0,0) in model space.
     */
    private Vector2 originLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(0, 0));
    }

    /**
     * @return the screen space coordinates of (width,height) in model space.
     */
    private Vector2 endLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(getWidth(), getHeight()));
    }
}
