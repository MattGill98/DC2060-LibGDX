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
    }

    DrawableActor(TextureRegion texture) {
        this(texture, 0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 originCoords = originLocalToScreenCoordinates();
        Vector2 endCoords = endLocalToScreenCoordinates();
        batch.draw(texture, originCoords.x, originCoords.y, endCoords.x - originCoords.x, endCoords.y - originCoords.y);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, -y, touchable);
    }

    private Vector2 originLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(0, 0));
    }

    private Vector2 endLocalToScreenCoordinates() {
        return localToScreenCoordinates(new Vector2(1, 1));
    }
}
