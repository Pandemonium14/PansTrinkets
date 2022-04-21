 package pansTrinkets.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cards.AbstractTrinket;

 @SpirePatch(
        clz= UseCardAction.class,
        method="update"
)
public class UseCardActionPatchAfterCardUse {
    @SpireInsertPatch(rloc = 3,localvars={""})
    public static void InsertCardOnAfterCardUse() {

        for (AbstractCard O : AbstractDungeon.player.hand.group) {
            if (AbstractTrinket.class.isAssignableFrom(O.getClass())) {
                ((AbstractTrinket) O).pansAfterCardUse(O);
            }
        }
    }
}
