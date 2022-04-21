package pansTrinkets.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import pansTrinkets.DefaultMod;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.DefaultMod.theDefaultDefaultSettings;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class SealedBox extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(SealedBox.class.getSimpleName());
    public static final String IMG = makeCardPath("SealedBox.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 2;

    public SealedBox() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 50;
        this.weight = 3;
        this.baseDurability = 1;
        this.durability = 1;
        this.hasDurability = true;
        this.purgeOnUse = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
        this.addToBot(new GainGoldAction(this.magicNumber));
        super.use();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(75);
            super.upgrade();
            this.initializeDescription();
        }
    }
}
