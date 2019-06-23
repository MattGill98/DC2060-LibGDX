package uk.aston.dc2060.model.health;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

    private final Texture lowHealthTexture;

    private final Texture mediumHealthTexture;

    private final Texture highHealthTexture;

    private final float maxHealth;
    private float currentHealth;

    public HealthBar(float maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        lowHealthTexture = new Texture(fromColor(new Color(1, 0, 0, 1)));
        mediumHealthTexture = new Texture(fromColor(new Color(1, 0.9f, 0.3f, 1)));
        highHealthTexture = new Texture(fromColor(new Color(0, 1, 0, 1)));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float barWidth = 0.5f * (currentHealth / maxHealth);
        batch.draw(getTexture(), 0.5f - (barWidth / 2f), 0.8f, barWidth, 0.04f);
    }

    public float getHealth() {
        return currentHealth;
    }

    public void modifyHealth(float delta) {
        this.currentHealth += delta;
    }

    private Texture getTexture() {
        if (currentHealth < maxHealth * 0.2f) {
            return lowHealthTexture;
        } else if (currentHealth < maxHealth * 0.5f) {
            return mediumHealthTexture;
        } else {
            return highHealthTexture;
        }
    }

    private static Pixmap fromColor(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(color);
        pixmap.fill();
        return pixmap;
    }
}
