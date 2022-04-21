package pansTrinkets.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import pansTrinkets.cards.AbstractTrinket;

public class DurabilityVariable extends DynamicVariable {

    @Override
    public String key()
    {
        return "DURABILITY";
        // What you put in your localization file between ! to show your value. Eg, !myKey!.
    }

    public boolean isModified(AbstractCard card)  {
        /*if (card instanceof AbstractTrinket){
            return ((AbstractTrinket) card).timesUsed == 0;
        } else {*/
            return false;

        // Set to true if the value is modified from the base value.
    }


    public int value(AbstractCard card){
        if (card instanceof AbstractTrinket) {
            return ((AbstractTrinket) card).baseDurability - ((AbstractTrinket) card).timesUsed;
        } else {
            return 0;
        }
        // What the dynamic variable will be set to on your card. Usually uses some kind of int you store on your card.
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractTrinket && ((AbstractTrinket) card).hasDurability) {
            return ((AbstractTrinket) card).baseDurability - ((AbstractTrinket) card).timesUsed;
        } else {
            return 0;
        }
        // Should generally just be the above.
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return card instanceof AbstractTrinket;
        // Set to true if this value is changed on upgrade
    }

}
