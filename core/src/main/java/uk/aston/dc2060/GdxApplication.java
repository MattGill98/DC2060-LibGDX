package uk.aston.dc2060;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.impl.AbstractLmlView;
import com.github.czyzby.lml.util.Lml;
import com.github.czyzby.lml.util.LmlApplicationListener;
import uk.aston.dc2060.view.*;
import uk.aston.dc2060.view.attributes.SrcAttribute;
import uk.aston.dc2060.view.tags.AnimationTagProvider;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GdxApplication extends LmlApplicationListener {

    public static Skin SKIN;

    private final ViewActions viewActions;

    public GdxApplication() {
        this.viewActions = new ViewActions(this);
    }

    @Override
    public void create() {
        // Call super
        super.create();

        // Initialise views
        GameLmlView gameLmlView = new GameLmlView(viewActions);
        initiateView(gameLmlView);
        initiateView(new PauseLmlView(viewActions));
        initiateView(new SummaryLmlView(() -> gameLmlView.getStage().getScore()));
        addClassAlias("menu", MenuLmlView.class);
        addClassAlias("game", GameLmlView.class);
        addClassAlias("pause", PauseLmlView.class);
        addClassAlias("summary", SummaryLmlView.class);
        setView(MenuLmlView.class);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1804f, 0.8f, 0.4431f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        if (getCurrentView() != null) {
            getCurrentView().render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public AbstractLmlView getView(Class<? extends AbstractLmlView> viewClass) {
        return super.getView(viewClass);
    }

    @Override
    public void initiateView(AbstractLmlView view) {
        super.initiateView(view);
    }

    @Override
    protected LmlParser createParser() {
        // Initialise GUI theme
        SKIN = new Skin(new TextureAtlas(Gdx.files.internal("theme/packed/theme.atlas")));
        SKIN.load(Gdx.files.internal("theme/Holo-dark-xhdpi.json"));

        return Lml
                .parser()
                .skin(SKIN)
                .i18nBundle(I18NBundle.createBundle(Gdx.files.internal("i18n/bundle")))
                .tag(new AnimationTagProvider(), "animation")
                .attribute(new SrcAttribute(), "src")
                .actions("viewActions", viewActions)
                .build();
    }
}