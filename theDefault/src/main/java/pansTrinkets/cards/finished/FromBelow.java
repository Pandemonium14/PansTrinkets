package pansTrinkets.cards.finished;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pansTrinkets.DefaultMod;
import pansTrinkets.cards.AbstractTrinket;

import java.util.Iterator;

import static pansTrinkets.DefaultMod.makeCardPath;

public class FromBelow extends AbstractTrinket {
    public static final String ID = DefaultMod.makeID(FromBelow.class.getSimpleName());
    public static final String IMG = makeCardPath("FromBelow.png");


    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;

    public FromBelow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();// 39
    }// 40

    public void onChoseThisOption() {
        Iterator var2 = AbstractDungeon.player.discardPile.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade()) {

                c.upgrade();
                c.applyPowers();
            }
        }

    }

    @Override
    public void upgrade() {

    }
}
