package pansTrinkets.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static pansTrinkets.DefaultMod.makeCardPath;
import static pansTrinkets.patches.EnumColorPatch.TRINKET_WHITE;

public class CorruptedIdol extends AbstractTrinket {

    public static final String ID = DefaultMod.makeID(CorruptedIdol.class.getSimpleName());
    public static final String IMG = makeCardPath("CorruptedIdol.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TRINKET_WHITE;

    private static final int COST = 1;

    public CorruptedIdol() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.weight = 1;
        this.cardStrings = languagePack.getCardStrings(ID);
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void onAddedToMasterDeck() {
        AbstractDungeon.player.increaseMaxHp(20, false);
    }

    @Override
    public void onRemoveFromMasterDeck() {
        if (AbstractDungeon.player.maxHealth > 20) {
            AbstractDungeon.player.decreaseMaxHealth(20);
        } else {
            AbstractDungeon.player.decreaseMaxHealth(AbstractDungeon.player.maxHealth-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }
}
