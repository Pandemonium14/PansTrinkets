package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;
import pansTrinkets.powers.ExplosiveTrapPower;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class ExplosiveTrap extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(ExplosiveTrap.class.getSimpleName());
    public static final String IMG = makeCardPath("ExplosiveTrap.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public ExplosiveTrap() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 15;
        this.isInnate = true;
        this.weight = 4;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p,p,new ExplosiveTrapPower(p,p, this.magicNumber)));
        super.use();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(6);
            super.upgrade();
            this.initializeDescription();
        }
    }
}
