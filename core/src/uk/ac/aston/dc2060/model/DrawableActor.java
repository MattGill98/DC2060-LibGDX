package uk.ac.aston.dc2060.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import uk.ac.aston.dc2060.controller.TowerDefenceStage;

/**
 * A class modelling a drawable object on screen.
 */
public abstract class DrawableActor extends Actor implements Drawable {

    protected DrawableActor(float x, float y) {
        super();
        setX(x);
        setY(y);

        // Needed for correct rendering
        setWidth(1);
        setHeight(1);
        setOrigin(getWidth() / 2f, getHeight() / 2f);
    }

    protected DrawableActor() {
        this(-1, -1);
    }

    /**
     * @param a the transparency value of the object, between 0 and 1.
     */
    public final void setAlpha(float a) {
        getColor().a = a;
    }

    @Override
    public final void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        draw(batch);
    }

    @Override
    public boolean remove() {
        setVisible(false);
        return super.remove();
    }

    public TowerDefenceStage getStage() {
        return (TowerDefenceStage) super.getStage();
    }
}
