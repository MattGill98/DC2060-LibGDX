package uk.aston.dc2060.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.czyzby.lml.annotation.LmlActor;
import com.github.czyzby.lml.annotation.LmlAfter;
import uk.aston.dc2060.controller.UpdateAction;
import uk.aston.dc2060.model.map.GameStage;
import uk.aston.dc2060.model.tower.DoubleTurret;
import uk.aston.dc2060.model.tower.SingleTurret;
import uk.aston.dc2060.model.tower.TowerManager;

public class GameLmlView extends CustomLmlView {

    private final OrthogonalTiledMapRenderer tiledMapRenderer;

    @LmlActor("score")
    private Label scoreLabel;

    @LmlActor("round")
    private Label roundLabel;

    @LmlActor("lives")
    private Label livesLabel;

    @LmlAfter
    public void initialise() {
        GameStage stage = getStage();
        scoreLabel.addAction(new UpdateAction(() -> stage.getScore()));
        roundLabel.addAction(new UpdateAction(() -> stage.getRound()));
        livesLabel.addAction(new UpdateAction(() -> stage.getLives()));
    }

    public GameLmlView(ViewActions viewActions) {
        super("game", new GameStage(new TmxMapLoader().load("level/tilemap.tmx"), 17, 12));

        // Configure pause button
        getStage().addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (event.getKeyCode() == Input.Keys.ESCAPE) {
                    viewActions.pause();
                }
                return false;
            }
        });

        // Configure map rendering
        OrthographicCamera mapCamera = new OrthographicCamera();
        mapCamera.setToOrtho(false, 17, 12);
        mapCamera.update();
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(getStage().getTiledMap(), 1 / 64f);
        this.tiledMapRenderer.setView(mapCamera);

        // Add towers
        TiledMapTileSets tileSet = getStage().getTiledMap().getTileSets();

        // Configure tower managers
        getStage().addActor(new TowerManager(getStage(), SingleTurret.class, tileSet, 16, 0));
        getStage().addActor(new TowerManager(getStage(), DoubleTurret.class, tileSet, 16, 1));

        // Configure game end
        getStage().addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (getStage().getLives() <= 0) {
                    viewActions.finishGame();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void clear() {
        getStage().clear();
    }

    @Override
    public GameStage getStage() {
        return (GameStage) super.getStage();
    }

    @Override
    public void render(float delta) {
        tiledMapRenderer.render();
        super.render(delta);
    }

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        super.dispose();
    }
}
