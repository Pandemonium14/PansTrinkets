package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.finished.Kaleidoscope;
import pansTrinkets.powers.EchoChamberPower;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class EchoChamber extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(EchoChamber.class.getSimpleName());
    public static final String IMG = makeCardPath("EchoChamber.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 3;

    public EchoChamber() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 4;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EchoChamberPower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            super.upgrade();
            initializeDescription();
        }
    }
}
