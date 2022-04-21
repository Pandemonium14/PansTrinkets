package pansTrinkets.util;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import pansTrinkets.DefaultMod;

import static pansTrinkets.DefaultMod.getModID;

public class WeightIcon extends AbstractCustomIcon {
    public static final String ID = DefaultMod.makeID("Weight");
    private static WeightIcon singleton;

    public WeightIcon() {
        super(ID, TextureLoader.getTexture("pansTrinketsResources/images/icon/WeightIcon.png"));
    }

    public static WeightIcon get()
    {
        if (singleton == null) {
            singleton = new WeightIcon();
        }
        return singleton;
    }
}
