package uk.aston.dc2060.view.tags;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.holidaystudios.tools.GifDecoder;

public class AnimationActor extends Image {

    private final Animation<TextureRegion> animation;
    private float elapsed;

    AnimationActor(int width, int height, String src) {
        setWidth(width);
        setHeight(height);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.NORMAL, Gdx.files.internal(src).read());
    }

    @Override
    public void act(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        setDrawable(new TextureRegionDrawable(animation.getKeyFrame(elapsed)));
        super.act(delta);
    }
}
