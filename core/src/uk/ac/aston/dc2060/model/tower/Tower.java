package uk.ac.aston.dc2060.model.tower;

public class Tower extends TowerIcon {

    public Tower(TowerIcon icon) {
        super(icon.getTexture());
        setAlpha(0.65f);
    }

    public void realise() {
        setAlpha(1f);
    }
}
