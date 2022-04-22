package pansTrinkets.rewards;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import pansTrinkets.actions.RemoveRewardEffect;
import pansTrinkets.patches.TrinketRewardTypePatch;

import java.util.ArrayList;

import static pansTrinkets.DefaultMod.getModID;

public class LinkedCardReward extends CustomReward {

    private static final Texture ICON = new Texture(Gdx.files.internal(getModID() + "Resources/images/icon/reward.png"));
    private final ArrayList<AbstractCard> cardChoices;
    public RewardItem linkedReward;
    public boolean isLinkedRewardTaken;

    public LinkedCardReward(ArrayList<AbstractCard> cards, TrinketReward reward) {
        super(ICON,"Add a card to your deck", TrinketRewardTypePatch.PANS_TRINKET_CARD_REWARD);
        linkedReward = reward;
        cardChoices = cards;
        isLinkedRewardTaken = false;
        if (reward != null) {
            reward.linkedReward = this;
        }
    }

    @Override
    public boolean claimReward() {
        if (linkedReward != null && isLinkedRewardTaken) {
            return true;
        }
        AbstractDungeon.cardRewardScreen.open(cardChoices,this, "Add a card to your deck");
        AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        return false;
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

}
