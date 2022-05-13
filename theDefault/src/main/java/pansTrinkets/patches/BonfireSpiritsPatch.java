package pansTrinkets.patches;


import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.Bonfire;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import pansTrinkets.helpers.TrinketHelper;

import java.lang.reflect.Array;


import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

@SpirePatch(clz = Bonfire.class, method = "update")
public class BonfireSpiritsPatch {

    public static final String gaveTrinketText = "You gave the spirits a nice item. They are happy and make your bag sturdier. You can carry more things now.";

    @SpireInsertPatch(locator = Locator.class)
    public static SpireReturn<Void> patch(Bonfire __instance, AbstractCard ___offeredCard, boolean ___cardSelect) {
        if (___offeredCard.color == TRINKET_WHITE) {
            switch (___offeredCard.rarity) {
                case UNCOMMON:
                    TrinketHelper.maxWeight += 3;
                    break;
                case RARE:
                    TrinketHelper.maxWeight += 6;
            }
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(___offeredCard, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));// 82
            AbstractDungeon.player.masterDeck.removeCard(___offeredCard);
            __instance.imageEventText.updateDialogOption(0, Bonfire.OPTIONS[1]);

            Object[] enumArray = null;
            try {
                enumArray = Class.forName("com.megacrit.cardcrawl.events.shrines.Bonfire$CUR_SCREEN").getEnumConstants();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ReflectionHacks.setPrivate(__instance, Bonfire.class, "screen", enumArray[2]);
            ___cardSelect = false;
            __instance.imageEventText.updateBodyText(gaveTrinketText);
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher matcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "rarity");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }
}
