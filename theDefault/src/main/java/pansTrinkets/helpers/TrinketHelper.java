package pansTrinkets.helpers;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cards.AbstractTrinket;

import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class TrinketHelper implements CustomSavable<Float> {

    public static final int BASE_MAX_WEIGHT = 5;
    public static int maxWeight = BASE_MAX_WEIGHT;
    public static Float maxWeightF = maxWeight + 0.0F ;


    public static int carriedWeight(AbstractPlayer p) {
        int w = 0;
        for (AbstractCard card : p.masterDeck.group) {
            if (card.color.equals(TRINKET_WHITE)) {
                w += ((AbstractTrinket)card).weight;
            }
        }
        return w;
    }

    public static boolean shouldTrinketDrop() {
        int r = AbstractDungeon.treasureRng.random(99);
        return r <= 15; // set here !!!!!!
    }

    public static void changeMaxWeight(int change) {
        maxWeightF += change;
        maxWeight += change;
    }

    public static void changeMaxWeight(float change) {
        maxWeightF += change;
        maxWeight = maxWeightF.intValue();
    }

    @Override
    public Float onSave() {
        return maxWeightF;
    }

    @Override
    public void onLoad(Float saved) {
        maxWeightF = saved;
        maxWeight = maxWeightF.intValue();
    }

    public static void onCardObtain(AbstractCard c) {
        maxWeightF += 0.5f;
        maxWeight = maxWeightF.intValue();
    }
}
