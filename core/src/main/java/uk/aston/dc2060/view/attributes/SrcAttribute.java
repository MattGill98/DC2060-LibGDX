package uk.aston.dc2060.view.attributes;

import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.tag.LmlAttribute;
import com.github.czyzby.lml.parser.tag.LmlTag;
import uk.aston.dc2060.view.tags.AnimationActor;

/**
 * The 'src' attribute for the animated image.
 */
public class SrcAttribute implements LmlAttribute<AnimationActor> {
    @Override
    public Class<AnimationActor> getHandledType() {
        return AnimationActor.class;
    }

    @Override
    public void process(LmlParser parser, LmlTag tag, AnimationActor animationActor, String rawAttributeData) {
    }
}
