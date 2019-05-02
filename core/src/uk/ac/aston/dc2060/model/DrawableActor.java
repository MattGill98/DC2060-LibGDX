package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * A class modelling a drawable object on screen.
 */
public abstract class DrawableActor extends Actor {

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

        // Needed for correct rendering
        setWidth(1);
        setHeight(1);
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

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
    public final void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        draw(batch);
    }

    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
