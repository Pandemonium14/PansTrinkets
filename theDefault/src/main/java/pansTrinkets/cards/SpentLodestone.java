package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SpentLodestone extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SpentLodestone.class.getSimpleName());
    public static final String IMG = makeCardPath("SpentLodestone.png");


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;

    public SpentLodestone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Lodestone();
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
    }

    public SpentLodestone(boolean showPreview) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = 1;
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
        if (showPreview) {
            cardsToPreview = new Lodestone(false);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            weight -= 2;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardPileToHandAction(1));
        AbstractTrinket c = new Lodestone();
        c.weight = 0;
        addToBot(new MakeTempCardInDiscardAction(c,1));
    }
}
