package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class ShieldingTalisman extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(ShieldingTalisman.class.getSimpleName());
    public static final String IMG = makeCardPath("ShieldingTalisman.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public ShieldingTalisman() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 2;
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new Guard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInDrawPileAction(new Guard(), magicNumber, true, true));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            super.upgrade();
            this.initializeDescription();
        }
    }
}

