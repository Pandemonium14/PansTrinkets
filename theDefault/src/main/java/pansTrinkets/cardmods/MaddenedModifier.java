package pansTrinkets.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MaddenedModifier extends AbstractCardModifier {
    public static String ID = "panstrinkets:Maddened";


    public void onInitialApplication(AbstractCard card) {
        if (card.cost > 0 ) {
            card.cost = 0;
            card.costForTurn = 0;
            card.isCostModified = true;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MaddenedModifier();
    }

}
