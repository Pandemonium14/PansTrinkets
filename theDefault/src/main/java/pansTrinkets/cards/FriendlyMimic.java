package pansTrinkets.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cardmods.ShiftingModifier;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class FriendlyMimic extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(FriendlyMimic.class.getSimpleName());
    public static final String IMG = makeCardPath("SkillTemplate.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = -2;

    public FriendlyMimic() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 3;
        CardModifierManager.addModifier(this,new ShiftingModifier(this.upgraded));
        this.cardStrings = languagePack.getCardStrings(ID);

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            CardModifierManager.removeAllModifiers(this, true);
            CardModifierManager.addModifier(this, new ShiftingModifier(true));
            super.upgrade();
            this.initializeDescription();
        }
    }
}