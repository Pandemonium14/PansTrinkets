package pansTrinkets.patches;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;


@SpirePatch(clz = ColorTabBarFix.Render.class, method = "Insert")
public class ColorTabPatch {
    private static final String TEXT ="Trinkets";

    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(ColorTabBar __instance, SpriteBatch sb, float y, ColorTabBar.CurrentTab curTab, @ByRef String[] ___tabName, int ___i) {
        ArrayList<ColorTabBarFix.ModColorTab> modTabs = ReflectionHacks.getPrivateStatic(ColorTabBarFix.Fields.class, "modTabs");
        if (modTabs.get(___i).color.equals(EnumColorPatch.TRINKET_WHITE)) {
            ___tabName[0] = TEXT;
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher matcher = new Matcher.MethodCallMatcher(FontHelper.class, "renderFontCentered");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }
}