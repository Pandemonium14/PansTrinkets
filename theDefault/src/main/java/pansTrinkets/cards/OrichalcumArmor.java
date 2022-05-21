package pansTrinkets.cards;

import basemod.BaseMod;
import basemod.interfaces.OnStartBattleSubscriber;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.finished.DimLantern;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class OrichalcumArmor extends AbstractTrinket implements StartupCard {

    public static final String ID = DefaultMod.makeID(OrichalcumArmor.class.getSimpleName());
    public static final String IMG = makeCardPath("OrichalcumArmor.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public OrichalcumArmor() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = 5;
        magicNumber = baseMagicNumber = 2;
        weight = 3;
        cardStrings = languagePack.getCardStrings(ID);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            super.upgrade();
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));
    }


    @Override
    public boolean atBattleStartPreDraw() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MetallicizePower(AbstractDungeon.player, magicNumber)));
        return true;
    }
}