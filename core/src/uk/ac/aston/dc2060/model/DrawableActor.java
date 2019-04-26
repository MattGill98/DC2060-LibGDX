package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DrawableActor extends Actor {

    final TextureRegion texture;

    DrawableActor(TextureRegion texture) {
        this.texture = texture;
    }

    DrawableActor(TextureRegion texture, int x, int y) {
        this.texture = texture;
        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Vector2 screenCoords = getStage().getViewport().project(new Vector2(getX(), getY()));
        Vector2 screenCoords2 = getStage().getViewport().project(new Vector2(getX() + 1, getY() + 1));
        batch.draw(texture, screenCoords.x, screenCoords.y, screenCoords2.x - screenCoords.x, screenCoords2.y - screenCoords.y);
    }

}
