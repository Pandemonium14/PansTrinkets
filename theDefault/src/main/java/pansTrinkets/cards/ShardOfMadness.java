package pansTrinkets.cards;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.ShardOfMadnessAction;
import pansTrinkets.helpers.TrinketHelper;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class ShardOfMadness extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(ShardOfMadness.class.getSimpleName());
    public static final String IMG = makeCardPath("ShardOfMadness.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 2;

    public ShardOfMadness() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
        this.weight = 4;
        this.cardStrings = languagePack.getCardStrings(ID);
        FleetingField.fleeting.set(this, true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-2);
            weight -= 2;
            super.upgrade();
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ShardOfMadnessAction());
        TrinketHelper.maxWeight -= magicNumber;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return getApplicableCards(p).size() != 0;
    }

    private static ArrayList<AbstractCard> getApplicableCards(AbstractPlayer p) {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
        for (AbstractCard c : p.hand.group) {
            AbstractCard masterDeckCard = StSLib.getMasterDeckEquivalent(c);
            if (c.cost > 0 && masterDeckCard != null) {
                cards.add(c);
            }
        }
        return cards;
    }

}
