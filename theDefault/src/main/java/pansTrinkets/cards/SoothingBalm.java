package pansTrinkets.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.BalmAction;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SoothingBalm extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SoothingBalm.class.getSimpleName());
    public static final String IMG = makeCardPath("SoothingBalm.png");


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;


    private static final int COST = 0;

    public SoothingBalm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.quickDraw = true;
        this.weight = 1;
        cardStrings = languagePack.getCardStrings(ID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BalmAction(AbstractDungeon.player));
        super.use();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.retain = true;
            this.selfRetain = true;
            super.upgrade();
            this.initializeDescription();
        }
    }
}

