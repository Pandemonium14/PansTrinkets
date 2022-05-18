package pansTrinkets.cards;

import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.LodestoneDrawPileAction;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class Lodestone extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(Lodestone.class.getSimpleName());
    public static final String IMG = makeCardPath("Lodestone.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;

    public Lodestone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = 1;
        this.weight = 3;
        cardsToPreview = new SpentLodestone(false);
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
    }

    public Lodestone(boolean showPreview) {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = 1;
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
        if (showPreview) {
            cardsToPreview = new SpentLodestone(false);
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
        addToBot(new LodestoneDrawPileAction(new SpentLodestone()));
    }
}
