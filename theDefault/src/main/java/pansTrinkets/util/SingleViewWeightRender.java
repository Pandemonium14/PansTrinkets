package pansTrinkets.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import pansTrinkets.cards.AbstractTrinket;

import java.util.EnumMap;
import java.util.Map;

public class SingleViewWeightRender {

    private static final Texture tex = ImageMaster.loadImage("pansTrinketsResources/images/icon/WeightSingleViewIcon.png");
    private static float yOffset = 120.0F * Settings.scale;
    private static float centerX = (float)Settings.WIDTH / 2.0F;
    private static float centerY = (float)Settings.HEIGHT / 2.0F;


    private static void renderElementHelper(SpriteBatch sb, Texture img, float drawX, float drawY) {

        sb.draw(img, drawX, drawY,
                0, 0, 164.0F, 164.0F,
                Settings.scale,  Settings.scale,
                0, 0, 0, 164, 164, false, false);

    }

    public static void renderElementCost(AbstractTrinket card, SpriteBatch sb){

        float fScaleX = FontHelper.SCP_cardEnergyFont.getData().scaleX;

        FontHelper.SCP_cardEnergyFont.getData().setScale(0.75F);

        if(!card.isLocked && card.isSeen) {

                // <----------
                if (card.weight != 0) {

                    renderElementHelper(sb, tex, centerX - 348.0F * Settings.scale,
                            centerY + 163.0F * Settings.scale);

                    Color c = Settings.CREAM_COLOR;
                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, card.weight+"", 682.0F * Settings.scale,
                            (float)Settings.HEIGHT / 2.0F + 268.0F * Settings.scale, c);

                }

        }
        FontHelper.SCP_cardEnergyFont.getData().setScale(fScaleX);
    }
}
