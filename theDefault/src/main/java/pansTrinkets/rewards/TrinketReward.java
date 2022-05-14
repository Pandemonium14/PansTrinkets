package pansTrinkets.rewards;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import pansTrinkets.actions.RemoveRewardEffect;
import pansTrinkets.helpers.TrinketLibrary;
import pansTrinkets.patches.TrinketRewardTypePatch;

import java.util.ArrayList;
import java.util.Iterator;

import static pansTrinkets.DefaultMod.getModID;

public class TrinketReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal(getModID() + "Resources/images/icon/trinketReward.png"));
    private final boolean isRare;
    public RewardItem linkedReward;
    public boolean isLinkedRewardTaken;


    public TrinketReward(boolean rare) {
        super(ICON,"Get a trinket", TrinketRewardTypePatch.PANS_TRINKET_TRINKET_REWARD);
        isRare = rare;
        cards = generateCardChoices();
        isLinkedRewardTaken = false;
    }

    public TrinketReward(boolean rare,ArrayList<AbstractCard> choices, RewardItem reward) {
        super(ICON,"Get a trinket", TrinketRewardTypePatch.PANS_TRINKET_TRINKET_REWARD);
        linkedReward = reward;
        isRare = rare;
        cards = choices;
        isLinkedRewardTaken = false;
    }


    @Override
    public boolean claimReward() {
        if (linkedReward != null && isLinkedRewardTaken) {
            return true;
        }
        AbstractDungeon.cardRewardScreen.open(cards,this, "Choose one trinket.");
        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        return false;
    }

    public static ArrayList<AbstractCard> generateCardChoices() {
        ArrayList derp = new ArrayList();// 57

        while(derp.size() != 3) {// 60
            boolean dupe = false;// 61
            int roll = AbstractDungeon.treasureRng.random(99);// !!! CHANGE THIS BACK TO 99 !!!
            AbstractCard.CardRarity cardRarity;
            if (roll < 0) {// 65
                cardRarity = AbstractCard.CardRarity.COMMON;// 66
            } else if (roll < 85) {// 67
                cardRarity = AbstractCard.CardRarity.UNCOMMON;// 68
            } else {
                cardRarity = AbstractCard.CardRarity.RARE;// 70
            }

            AbstractCard tmp = TrinketLibrary.getTrinket(cardRarity);// 73
            Iterator var6 = derp.iterator();// 75

            while(var6.hasNext()) {
                AbstractCard c = (AbstractCard)var6.next();
                if (c.cardID.equals(tmp.cardID)) {// 76
                    dupe = true;// 77
                    break;// 78
                }
            }

            if (!dupe) {// 82
                derp.add(tmp.makeCopy());// 83
            }
        }

        return derp;// 87
    }


    @Override
    public void update() {
        super.update();
        if (linkedReward != null && !AbstractDungeon.combatRewardScreen.rewards.contains(linkedReward) && AbstractDungeon.combatRewardScreen.rewards.contains(this)) {
            this.isLinkedRewardTaken = true;
            this.ignoreReward = true;
            AbstractDungeon.effectList.add(new RemoveRewardEffect(this)); //you get a crash if you remove the reward directly in here
        }
    }

    private void renderLink(SpriteBatch sb) {
        sb.setColor(Color.WHITE);// 643
        sb.draw(ImageMaster.RELIC_LINKED, this.hb.cX - 64.0F, this.y - 64.0F + 52.0F * Settings.scale, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);// 644
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (!isLinkedRewardTaken && linkedReward != null) {
            renderLink(sb);
        }
    }
}
