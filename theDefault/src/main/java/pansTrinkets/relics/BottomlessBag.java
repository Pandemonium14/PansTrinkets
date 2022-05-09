package pansTrinkets.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import pansTrinkets.DefaultMod;
import pansTrinkets.util.TextureLoader;

import static pansTrinkets.DefaultMod.makeRelicOutlinePath;
import static pansTrinkets.DefaultMod.makeRelicPath;

public class BottomlessBag extends CustomRelic {

    public static final String ID = DefaultMod.makeID("PlaceholderRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));


    public BottomlessBag() {
       super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

}