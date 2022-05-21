package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.powers.FuryPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SymbolOfFury extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SymbolOfFury.class.getSimpleName());
    public static final String IMG = makeCardPath("SymbolOfFury.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public SymbolOfFury() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = 1;
        this.weight = 3;
        cardsToPreview = new UnboundFury();
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new FuryPower()));
        AbstractCard cardToAdd = new UnboundFury();
        if (upgraded) cardToAdd.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(cardToAdd,1, true, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            cardsToPreview.upgrade();
            this.initializeDescription();
        }
    }
}
