package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class DimLantern extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(DimLantern.class.getSimpleName());
    public static final String IMG = makeCardPath("DimLantern.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;

    public DimLantern() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.quickDraw = true;
        this.weight = 3;
        cardStrings = languagePack.getCardStrings(ID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedBluePower(p, magicNumber), magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.upgradeMagicNumber(1);
            super.upgrade();
            this.initializeDescription();
        }
    }


}
