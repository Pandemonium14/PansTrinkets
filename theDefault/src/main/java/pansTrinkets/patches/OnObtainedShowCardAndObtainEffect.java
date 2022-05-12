package pansTrinkets.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import pansTrinkets.cards.AbstractTrinket;

import java.util.ArrayList;

@SpirePatch(clz= ShowCardAndObtainEffect.class, method = "update")
public class OnObtainedShowCardAndObtainEffect {
    @SpireInsertPatch(locator = Locator.class)
    public static void patch(ShowCardAndObtainEffect __Instance, AbstractCard ___card) {

        if (___card instanceof AbstractTrinket) {
            ((AbstractTrinket)___card).onAddedToMasterDeck();
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher matcher = new Matcher.FieldAccessMatcher(ShowCardAndObtainEffect.class, "isDone");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }
}
