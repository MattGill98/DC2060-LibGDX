package uk.ac.aston.dc2060.model.health;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import uk.ac.aston.dc2060.model.Disposable;
import uk.ac.aston.dc2060.model.Drawable;

public class HealthBar implements Drawable, Disposable {

    private final float lowHealthMarker = 0.2f;
    private final Texture lowHealthTexture;

    private final float mediumHealthMarker = 0.5f;
    private final Texture mediumHealthTexture;

    private final Texture highHealthTexture;

    private float currentHealth = 1;

    private float x;
    private float y;

    public HealthBar() {
        lowHealthTexture = new Texture(fromColor(new Color(1, 0, 0, 1)));
        mediumHealthTexture = new Texture(fromColor(new Color(1, 0.9f, 0.3f, 1)));
        highHealthTexture = new Texture(fromColor(new Color(0, 1, 0, 1)));
    }

    @Override
    public void draw(Batch batch) {
        float barWidth = 0.5f * currentHealth;
        batch.draw(getTexture(), x + 0.5f - (barWidth / 2f), y, barWidth, 0.04f);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHealth() {
        return currentHealth;
    }

    public void setHealth(float health) {
        this.currentHealth = health;
    }

    public void modifyHealth(float delta) {
        this.currentHealth += delta;
    }

    private Texture getTexture() {
        if (currentHealth < lowHealthMarker) {
            return lowHealthTexture;
        } else if (currentHealth < mediumHealthMarker) {
            return mediumHealthTexture;
        } else {
            return highHealthTexture;
        }
    }

    @Override
    public void dispose() {
        lowHealthTexture.dispose();
        mediumHealthTexture.dispose();
        highHealthTexture.dispose();
    }

    private static Pixmap fromColor(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        return pixmap;
    }
}
