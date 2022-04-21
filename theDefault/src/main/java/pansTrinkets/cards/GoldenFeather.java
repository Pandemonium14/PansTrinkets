package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class GoldenFeather extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(GoldenFeather.class.getSimpleName());
    public static final String IMG = makeCardPath("GoldenFeather.png");


    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;

    public GoldenFeather() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 1;
        this.exhaust = true;
        this.cardStrings = languagePack.getCardStrings(ID);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList();// 42
        stanceChoices.add(new FromBelow());// 43
        stanceChoices.add(new RiseAbove());
        this.addToBot(new ChooseOneAction(stanceChoices));
        if (upgraded) {
            addToBot(new ArmamentsAction(true));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            super.upgrade();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
