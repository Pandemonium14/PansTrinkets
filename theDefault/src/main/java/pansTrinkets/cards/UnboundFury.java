package pansTrinkets.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.powers.FuryPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class UnboundFury extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(UnboundFury.class.getSimpleName());
    public static final String IMG = makeCardPath("UnboundFury.png");


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;

    public UnboundFury() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = 0;
        this.cardStrings = languagePack.getCardStrings(ID);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (magicNumber > 0) addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        if (!AbstractDungeon.player.hasPower(FuryPower.POWER_ID)) {
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FuryPower()));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            //rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public boolean canPlay(AbstractCard c) {
        return c.type != CardType.SKILL || c instanceof UnboundFury;
    }
}
