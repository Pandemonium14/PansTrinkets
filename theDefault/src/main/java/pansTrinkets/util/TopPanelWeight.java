package pansTrinkets.util;

import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import pansTrinkets.DefaultMod;
import pansTrinkets.helpers.TrinketHelper;

public class TopPanelWeight extends TopPanelItem {

    public static final String ID = DefaultMod.makeID(TopPanelItem.class.getSimpleName());
    public static final UIStrings strings = CardCrawlGame.languagePack.getUIString("pansTrinkets:TopPanelWeightIcon");

    private int maxWeight;
    private int currentWeight;


    private static Texture ICON = new Texture("pansTrinketsResources/images/icon/WeightIcon.png");

    public TopPanelWeight() {
        super(ICON, ID);
        maxWeight = 0;
        currentWeight = 0;
    }

    @Override
    protected void onClick() {
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        maxWeight = TrinketHelper.maxWeight;
        currentWeight = TrinketHelper.carriedWeight(AbstractDungeon.player);

        float renderX = x + 35f * Settings.scale;
        float renderY = y + 30f * Settings.scale;

        FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, currentWeight + "/" + maxWeight, renderX, renderY, Color.WHITE);
    }

    @Override
    public void onHover() {
        maxWeight = TrinketHelper.maxWeight;
        currentWeight = TrinketHelper.carriedWeight(AbstractDungeon.player);

        TipHelper.renderGenericTip(x, y - Settings.HEIGHT / 26.0f, strings.TEXT[0], strings.EXTRA_TEXT[0]);
    }



}
