package uk.aston.dc2060.view;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.czyzby.lml.annotation.LmlActor;
import com.github.czyzby.lml.annotation.LmlAfter;

import java.util.function.Supplier;

public class SummaryLmlView extends CustomLmlView {

    public static final String ID = "summary";

    private final Supplier<Integer> scoreSupplier;

    @LmlActor("score")
    private Label scoreLabel;

    @LmlAfter
    public void initialise() {
        scoreLabel.setText(scoreLabel.getText() + " " + scoreSupplier.get().toString());
    }

    public SummaryLmlView(Supplier<Integer> scoreSupplier) {
        super(ID);
        this.scoreSupplier = scoreSupplier;
    }
}
