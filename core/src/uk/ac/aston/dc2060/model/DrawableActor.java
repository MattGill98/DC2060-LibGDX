package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DrawableActor extends Actor {

    private final TextureRegion texture;

    DrawableActor(TextureRegion texture, int x, int y) {
        this.texture = texture;
        setX(x);
        setY(y);

        // Needed for collision detection
        setWidth(1);
        setHeight(1);
    }

    protected DrawableActor(TextureRegion texture) {
        this(texture, 0, 0);
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setAlpha(float a) {
        setColor(1, 1, 1, a);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 originCoords = originLocalToScreenCoordinates();
        Vector2 endCoords = endLocalToScreenCoordinates();
        batch.setColor(getColor());
        batch.draw(texture, originCoords.x, originCoords.y, endCoords.x - originCoords.x, endCoords.y - originCoords.y);
    }

    private Vector2 originLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(0, 0));
    }

    private Vector2 endLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(getWidth(), getHeight()));
    }
}
