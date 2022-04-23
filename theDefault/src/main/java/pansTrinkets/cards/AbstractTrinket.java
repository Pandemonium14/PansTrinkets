package pansTrinkets.cards;

import basemod.abstracts.CustomSavable;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.ExceptionHandler;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import pansTrinkets.actions.LargeAction;
import pansTrinkets.cardmods.ShiftingModifier;
import pansTrinkets.helpers.TrinketRewardHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractTrinket extends AbstractDefaultCard implements CustomSavable<Integer> {

    public boolean quickDraw = false;
    public boolean hasDurability = false;
    public int durability = 0;
    public int baseDurability = durability;
    public boolean willBreak = false;
    public int timesUsed = 0;
    public int weight = 0;
    public boolean large = false;
    public boolean enableOnDrawActions = true;
    private Color renderColor = Color.WHITE.cpy();
    public CardStrings cardStrings;

    @Override
    public boolean canPlay(AbstractCard card) {
        return super.canPlay(card);
    }

    public AbstractTrinket(final String id,
                           final String img,
                           final int cost,
                           final CardType type,
                           final CardColor color,
                           final CardRarity rarity,
                           final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }
    public void pansAfterCardUse(AbstractCard c){
    }

    public void triggerWhenDrawn(){
        if (!CardModifierManager.hasModifier(this, ShiftingModifier.ID)) {
            if (this.quickDraw) {
                addToBot(new DrawCardAction(1));
            }
            if (this.large) {
                addToBot(new LargeAction(this));
            }
        }
    }

    public void use() {
        if (hasDurability) {

            AbstractTrinket c = (AbstractTrinket) StSLib.getMasterDeckEquivalent(this);

            this.timesUsed += 1;
            this.durability = this.baseDurability - timesUsed;

            if (c!=null) {
                c.timesUsed += 1;
                c.durability = baseDurability - timesUsed;
                if (c.durability == 0) {
                    AbstractDungeon.player.masterDeck.removeCard(c);
                }
            }
            if (willBreak) {
                purgeOnUse = true;
            }
            if (durability == 1) {
                willBreak = true;
            }

        }
    }

    public void upgrade() {
        durability = baseDurability - timesUsed;
    }

    public AbstractTrinket makeStatEquivalentCopy() {
        AbstractTrinket c = (AbstractTrinket) super.makeStatEquivalentCopy();
        c.baseDurability = this.baseDurability;
        c.timesUsed = this.timesUsed;
        c.durability = c.baseDurability - c.timesUsed;
        return c;
    }

    @Override
    public Integer onSave() {
        return timesUsed;
    }

    public void onLoad(Integer t) {
        timesUsed = t;
        durability = baseDurability - timesUsed;
    }

    @Override
    public Type savedType()
    {
        return new TypeToken<Integer>(){}.getType();
    }


    public int getWeight() {
        return weight;
    }

    public void displayWeight(SpriteBatch sb) {
        if (weight == 0 ) {return;}
        float drawX = this.current_x - 256.0F;
        float drawY = this.current_y - 256.0F;

        Texture icon = ImageMaster.loadImage("pansTrinketsResources/images/icon/WeightIcon.png");

        if(!this.isLocked && this.isSeen) {
            float yOffset = 94.0F * Settings.scale * this.drawScale;
            Vector2 offset = new Vector2(89.0F * this.drawScale * Settings.scale, -yOffset);
            offset.rotate(this.angle);
            this.renderHelper(sb, this.renderColor, icon, drawX + offset.x, drawY + offset.y);

            String msg = weight + "";
            Color weightColor = Color.WHITE;
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD && (weight + TrinketRewardHelper.carriedWeight(AbstractDungeon.player) > TrinketRewardHelper.maxWeight)) {
                weightColor = Color.RED;
            }
            FontHelper.renderRotatedText(sb, getWeightFont(this), msg, this.current_x,
                    this.current_y, -132.0F * this.drawScale * Settings.scale,
                    129.0F * this.drawScale * Settings.scale, this.angle,
                    true, weightColor);
        }
    }

    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX, drawY,
                256.0F, 256.0F, 512.0F, 512.0F,
                this.drawScale * Settings.scale, this.drawScale * Settings.scale,
                this.angle, 0, 0, 512, 512, false, false);


    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tips = new ArrayList<TooltipInfo>();
        tips.add(new TooltipInfo("[pansTrinkets:WeightIcon] Weight","You can only carry trinkets of a total weight lower than 10."));
        return tips;
    }

    private static BitmapFont getWeightFont(AbstractCard card) {
        FontHelper.cardEnergyFont_L.getData().setScale(card.drawScale * 0.75f);
        return FontHelper.cardEnergyFont_L;
    }
}