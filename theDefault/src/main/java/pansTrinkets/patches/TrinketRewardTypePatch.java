package pansTrinkets.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class TrinketRewardTypePatch {
    @SpireEnum
    public static RewardItem.RewardType PANS_TRINKET_TRINKET_REWARD;

    @SpireEnum
    public static RewardItem.RewardType PANS_TRINKET_CARD_REWARD;
}