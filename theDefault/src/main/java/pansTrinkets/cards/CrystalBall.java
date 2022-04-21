package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class CrystalBall extends AbstractTrinket {

    //Need to change/delete this card, the effect is too tedious

    public static final String ID = DefaultMod.makeID(CrystalBall.class.getSimpleName());
    public static final String IMG = makeCardPath("SkillTemplate.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public CrystalBall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.isInnate = true;
        this.weight = 3;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new Dazed();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int num = abstractPlayer.drawPile.size();
        addToBot(new ScryAction(num));
        addToBot(new MakeTempCardInDrawPileAction(new Dazed(), magicNumber, true, true));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            super.upgrade();
            this.upgradeMagicNumber(-1);
            this.initializeDescription();
        }
    }
}
