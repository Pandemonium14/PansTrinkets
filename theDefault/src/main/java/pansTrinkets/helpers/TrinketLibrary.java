package pansTrinkets.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.patches.EnumColorPatch;

import javax.smartcardio.Card;
import java.util.*;

import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class TrinketLibrary {

    public static Set<Map.Entry<String, AbstractCard>> allCardsSet = CardLibrary.cards.entrySet();
    public static CardGroup allCards = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup allTrinkets = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup CommonTrinkets = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup UncommonTrinkets = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
    public static CardGroup RareTrinkets = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

    public static void makeLists() {
        Iterator<Map.Entry<String, AbstractCard>> iter = allCardsSet.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, AbstractCard> e = iter.next();
            String key = e.getKey();
            AbstractCard c = e.getValue();
            allCards.group.add(c);
            if (c.color.equals(TRINKET_WHITE)) {
                if (!c.rarity.equals(AbstractCard.CardRarity.SPECIAL)) {
                    allTrinkets.group.add(c);
                }
                if (c.rarity.equals(AbstractCard.CardRarity.COMMON)) {
                    CommonTrinkets.group.add(c);
                } else if (c.rarity.equals(AbstractCard.CardRarity.UNCOMMON)) {
                    UncommonTrinkets.group.add(c);
                } else if (c.rarity.equals(AbstractCard.CardRarity.RARE)) {
                    RareTrinkets.group.add(c);
                }
            }
        }
    }

    public static AbstractCard getTrinket(AbstractCard.CardRarity rarity) {
        if (rarity.equals(AbstractCard.CardRarity.COMMON)) {
            return CommonTrinkets.getRandomCard(true);
        } else if (rarity.equals(AbstractCard.CardRarity.UNCOMMON)) {
            return UncommonTrinkets.getRandomCard(true);
        } else if (rarity.equals(AbstractCard.CardRarity.RARE)) {
            return RareTrinkets.getRandomCard(true);
        } else {
            return allTrinkets.getRandomCard(true);
        }
    }

    public static CardGroup getPlayersTrinkets(AbstractPlayer p) {
        CardGroup trinkets = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator iter = p.masterDeck.group.iterator();
        while (iter.hasNext()) {
            AbstractCard card = (AbstractCard) iter.next();
            if (card.color.equals(TRINKET_WHITE)) {
                trinkets.group.add(card);
            }
        }
        return trinkets;
    }

    public static void makeCharacterLists(AbstractPlayer.PlayerClass chosenClass) {
        Iterator<Map.Entry<String, AbstractCard>> iter = allCardsSet.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, AbstractCard> e = iter.next();
            String key = e.getKey();
            AbstractCard c = e.getValue();
            if (c.color.equals(TRINKET_WHITE)) {
                AbstractTrinket trinket = (AbstractTrinket) c;
                if (trinket.availableFor.size() == 0) {
                    addToLibrary(trinket);
                } else if (trinket.availableFor.contains(chosenClass)) {
                    addToLibrary(trinket);
                }
            }
        }
    }

    public static void addToLibrary(AbstractTrinket t) {
        if (!t.rarity.equals(AbstractCard.CardRarity.SPECIAL)) {
            allTrinkets.group.add(t);
        }
        if (t.rarity.equals(AbstractCard.CardRarity.COMMON)) {
            CommonTrinkets.group.add(t);
        } else if (t.rarity.equals(AbstractCard.CardRarity.UNCOMMON)) {
            UncommonTrinkets.group.add(t);
        } else if (t.rarity.equals(AbstractCard.CardRarity.RARE)) {
            RareTrinkets.group.add(t);
        }
    }

}
