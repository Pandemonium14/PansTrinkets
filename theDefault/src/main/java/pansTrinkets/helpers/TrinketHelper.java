package pansTrinkets.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pansTrinkets.cards.AbstractTrinket;

import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class TrinketHelper {

    public static int baseMaxWeight = 10;
    public static int maxWeight = baseMaxWeight;

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
        return r <= 10; // set here !!!!!!
    }
}
