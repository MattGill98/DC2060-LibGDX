package uk.ac.aston.dc2060.model.number;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.ac.aston.dc2060.model.DrawableActor;
import uk.ac.aston.dc2060.model.TileID;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static uk.ac.aston.dc2060.TowerDefenceGame.TILE_MAP;

public class Number extends DrawableActor {

    private final List<TextureRegion> numberTexture;

    private final Supplier<Integer> numberSupplier;
    private String lastNumber;

    public Number(int x, int y, Supplier<Integer> numberSupplier) {
        setX(x);
        setY(y);
        this.numberSupplier = numberSupplier;
        this.numberTexture = new ArrayList<>();
        fetchNumber();
    }

    @Override
    public void draw(Batch batch) {
        fetchNumber();
        for (int i = 0; i < numberTexture.size(); i++) {
            batch.draw(numberTexture.get(i), getCharacterX(i), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    private float getCharacterX(int position) {
        return getX() - 0.25f + ((getWidth() - 0.55f) * position);
    }

    private void fetchNumber() {
        String numberString = String.valueOf(numberSupplier.get()).replace("-", "");

        // Ignore if the number hasn't changed
        if (numberString.equals(lastNumber)) {
            return;
        }
        lastNumber = numberString;

        numberTexture.clear();
        for (int i = 0; i < numberString.length(); i++) {
            TileID numberTextureID = null;
            switch (numberString.charAt(i)) {
                case '0':
                    numberTextureID = TileID.ZERO;
                    break;
                case '1':
                    numberTextureID = TileID.ONE;
                    break;
                case '2':
                    numberTextureID = TileID.TWO;
                    break;
                case '3':
                    numberTextureID = TileID.THREE;
                    break;
                case '4':
                    numberTextureID = TileID.FOUR;
                    break;
                case '5':
                    numberTextureID = TileID.FIVE;
                    break;
                case '6':
                    numberTextureID = TileID.SIX;
                    break;
                case '7':
                    numberTextureID = TileID.SEVEN;
                    break;
                case '8':
                    numberTextureID = TileID.EIGHT;
                    break;
                case '9':
                    numberTextureID = TileID.NINE;
                    break;
                default:
                    throw new IllegalStateException("Cannot find texture. Unrecognised number: " + numberString);
            }
            numberTexture.add(TILE_MAP.getTileSets().getTile(numberTextureID.getID()).getTextureRegion());
        }
    }
}
