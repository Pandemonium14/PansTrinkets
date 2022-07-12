package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.AmorphousMatterAction;
import pansTrinkets.cards.AbstractTrinket;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class AmorphousMatter extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(AmorphousMatter.class.getSimpleName());
    public static final String IMG = makeCardPath("AmorphousMatter.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;

    public AmorphousMatter() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 4;
        this.cardStrings = languagePack.getCardStrings(ID);

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AmorphousMatterAction(upgraded));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            super.upgrade();
            this.initializeDescription();
        }
    }
}