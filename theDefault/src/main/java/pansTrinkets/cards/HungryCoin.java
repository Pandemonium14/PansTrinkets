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

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class HungryCoin extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(HungryCoin.class.getSimpleName());
    public static final String IMG = makeCardPath("HungryCoin.png");


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;
    private int increment = 3;

    public HungryCoin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 3;
        this.baseMagicNumber = 10;
        this.weight = 1;


        this.cardStrings = languagePack.getCardStrings(ID);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return p.gold >= baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        abstractPlayer.loseGold(baseMagicNumber);
        addToBot( new DamageAction(abstractMonster, new DamageInfo(abstractMonster, this.damage)));
        this.baseDamage += increment;
        //this.baseMagicNumber += increment;
        AbstractCard masterCard = StSLib.getMasterDeckEquivalent(this);
        if (masterCard != null) {
            masterCard.baseDamage += increment;
            //masterCard.baseMagicNumber += increment;
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
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            increment = 5;
            super.upgrade();
            this.initializeDescription();
        }
    }
}
