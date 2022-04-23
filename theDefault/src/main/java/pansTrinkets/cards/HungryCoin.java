package pansTrinkets.cards;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import pansTrinkets.DefaultMod;

import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class HungryCoin extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(HungryCoin.class.getSimpleName());
    public static final String IMG = makeCardPath("AttackTemplate.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 0;
    private static final int INCREMENT = 3;

    public HungryCoin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 3;
        this.baseMagicNumber = 3;
        this.weight = 1;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.gold >= baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        abstractPlayer.loseGold(baseMagicNumber);
        addToBot( new DamageAction(abstractMonster, new DamageInfo(abstractMonster, this.damage)));
        this.baseDamage += INCREMENT;
        this.baseMagicNumber += INCREMENT;
        AbstractCard masterCard = StSLib.getMasterDeckEquivalent(this);
        if (masterCard != null) {
            masterCard.baseDamage += INCREMENT;
            masterCard.baseMagicNumber += INCREMENT;
        }
        this.makeVisualEffects(abstractPlayer, abstractMonster);
    }

    public void makeVisualEffects(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.3F));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.quickDraw = true;
            this.rawDescription = "panstrinkets:Quickdraw NL Lose !M! Gold. Deal !D! damage. Permanently increase this card's damage and gold cost by 3.";
            super.upgrade();
            this.initializeDescription();
        }
    }
}
