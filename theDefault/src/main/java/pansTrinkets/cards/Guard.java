package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class Guard extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(Guard.class.getSimpleName());
    public static final String IMG = makeCardPath("SkillTemplate.png");


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;

    public Guard() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 6;
        this.quickDraw = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            super.upgrade();
            this.initializeDescription();
        }
    }
}
