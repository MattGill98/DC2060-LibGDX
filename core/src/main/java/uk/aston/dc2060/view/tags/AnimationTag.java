package uk.aston.dc2060.view.tags;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ObjectMap;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.impl.tag.actor.ImageLmlTag;
import com.github.czyzby.lml.parser.tag.LmlActorBuilder;
import com.github.czyzby.lml.parser.tag.LmlTag;

public class AnimationTag extends ImageLmlTag {

    AnimationTag(final LmlParser parser, final LmlTag parentTag, final StringBuilder rawTagData) {
        super(parser, parentTag, rawTagData);
    }

    @Override
    protected Actor getNewInstanceOfActor(LmlActorBuilder builder) {
        int width = Integer.parseInt(getAttribute("width"));
        int height = Integer.parseInt(getAttribute("height"));
        String src = getAttribute("src");
        return new AnimationActor(width, height, src);
    }

    @Override
    public ObjectMap<String, String> getNamedAttributes() {
        return super.getNamedAttributes();
    }
}
