package pansTrinkets.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.powers.EchoChamberPower;
import pansTrinkets.powers.SuperconductivePower;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

@AutoAdd.Ignore
public class MiracleCoolant extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(MiracleCoolant.class.getSimpleName());
    public static final String IMG = makeCardPath("MiracleCoolant.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public MiracleCoolant() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.weight = 3;
        magicNumber = baseMagicNumber = 1;
        availableFor.add(AbstractPlayer.PlayerClass.DEFECT);

        setBackgroundTexture("pansTrinketsResources/images/512/bg_trinket_blue_power.png","pansTrinketsResources/images/1024/bg_trinket_blue_power.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SuperconductivePower(p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
