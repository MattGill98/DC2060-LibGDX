package uk.aston.dc2060.view.tags;

import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.impl.tag.actor.provider.AnimatedImageLmlTagProvider;
import com.github.czyzby.lml.parser.tag.LmlTag;

public class AnimationTagProvider extends AnimatedImageLmlTagProvider {
    @Override
    public LmlTag create(LmlParser parser, LmlTag parentTag, StringBuilder rawTagData) {
        return new AnimationTag(parser, parentTag, rawTagData);
    }
}
