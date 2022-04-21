package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import java.util.Iterator;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SapphireLocket extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SapphireLocket.class.getSimpleName());
    public static final String IMG = makeCardPath("SapphireLocket.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;

    public SapphireLocket() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.quickDraw = true;
        this.isEthereal = true;
        this.isInnate = true;
        this.exhaust = true;
        this.weight = 3;
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardStrings = languagePack.getCardStrings(ID);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawPileToHandAction(this.magicNumber, CardType.POWER));
        super.use();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.quickDraw = true;
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            super.upgrade();
            this.initializeDescription();
        }
    }

}
