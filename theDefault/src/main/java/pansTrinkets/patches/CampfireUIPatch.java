package pansTrinkets.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import pansTrinkets.campfire.ThrowTrinketOption;
import pansTrinkets.helpers.TrinketLibrary;

import java.util.ArrayList;

@SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
public class CampfireUIPatch {
    @SpireInsertPatch(rloc = 5)
    public static void patch(CampfireUI __Instance) {
        ArrayList<AbstractCampfireOption> buttons = ReflectionHacks.getPrivate(__Instance,CampfireUI.class,"buttons");
        //if (TrinketLibrary.getPlayersTrinkets(AbstractDungeon.player).size() > 0 ) {
            buttons.add(new ThrowTrinketOption());
            ReflectionHacks.setPrivate(__Instance,CampfireUI.class,"buttons",buttons);
        //}
    }
}
