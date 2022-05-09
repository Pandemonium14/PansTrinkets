package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import pansTrinkets.DefaultMod;
import pansTrinkets.actions.FangHammerAction;
import pansTrinkets.cards.AbstractTrinket;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class FangHammer extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(FangHammer.class.getSimpleName());
    public static final String IMG = makeCardPath("FangHammer.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 2;

    public FangHammer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 14;
        this.weight = 4;

        availableFor.add(AbstractPlayer.PlayerClass.IRONCLAD);
        availableFor.add(AbstractPlayer.PlayerClass.THE_SILENT);
        availableFor.add(AbstractPlayer.PlayerClass.DEFECT);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            super.upgrade();
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {// 35
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));// 36
            this.addToBot(new WaitAction(0.8F));// 37
        }
        this.addToBot(new FangHammerAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
}
