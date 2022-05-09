package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class UniversalDictionary extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(UniversalDictionary.class.getSimpleName());
    public static final String IMG = makeCardPath("UniversalDictionary.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;
    private static final int MAGIC = 6;

    public UniversalDictionary() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.large = true;
        this.weight = 4;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        addToBot(new DrawCardAction(magicNumber));

    }

    @Override
    public void upgrade() {
        super.upgrade();
        if (!this.upgraded) {
            this.upgradeName();
            super.upgrade();
            weight = 2;
            this.initializeDescription();
        }
    }
}
