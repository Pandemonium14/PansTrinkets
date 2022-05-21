package pansTrinkets.patches;


import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import pansTrinkets.helpers.TrinketHelper;
import pansTrinkets.util.OnObtainedCard;

@SpirePatch2(clz= Soul.class, method = "obtain")
public class OnObtainedCardPatch {
    @SpirePrefixPatch
    public static void patch(AbstractCard card) {

        if (card instanceof OnObtainedCard) {
            ((OnObtainedCard)card).onObtained();
        }
        TrinketHelper.onCardObtain(card);
    }
}
