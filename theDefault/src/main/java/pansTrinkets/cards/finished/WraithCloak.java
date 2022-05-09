package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class WraithCloak extends AbstractTrinket {
        public static final String ID = DefaultMod.makeID(WraithCloak.class.getSimpleName());
        public static final String IMG = makeCardPath("WraithCloak.png");


        private static final CardRarity RARITY = CardRarity.RARE;
        private static final CardTarget TARGET = CardTarget.SELF;
        private static final CardType TYPE = CardType.SKILL;
        public static final CardColor COLOR = TRINKET_WHITE;

        private static final int COST = 2;

        public WraithCloak() {
            super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
            this.magicNumber = this.baseMagicNumber = 1;
            this.weight = 4;
            this.isEthereal = true;
            cardStrings = languagePack.getCardStrings(ID);


        }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot( new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.isEthereal= false;
            super.upgrade();
            this.initializeDescription();
        }
    }
}
