package pansTrinkets.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import pansTrinkets.cards.AbstractTrinket;

@SpirePatch(clz = AbstractCard.class,
        method = "renderEnergy",
        paramtypes = { "com.badlogic.gdx.graphics.g2d.SpriteBatch"})
public class WeightRenderPatch {
    public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
        if (__instance instanceof AbstractTrinket) {
            ((AbstractTrinket)__instance).displayWeight(sb);
        }
    }
}