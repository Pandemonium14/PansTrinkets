package pansTrinkets.campfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import pansTrinkets.helpers.TrinketLibrary;

public class ThrowTrinketEffect extends AbstractGameEffect {

    private static final float DUR = 1.5F;
    private boolean openedScreen = false;
    private Color screenColor;

    public ThrowTrinketEffect() {
        this.screenColor = AbstractDungeon.fadeColor.cpy();// 41
        this.duration = 1.5F;// 42
        this.screenColor.a = 0.0F;// 43
        AbstractDungeon.overlayMenu.proceedButton.hide();// 44
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {// 40
            this.duration -= Gdx.graphics.getDeltaTime();// 41
            this.updateBlackScreenColor();// 42
        }

        if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forPurge) {// 46
            AbstractCard card = (AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0);// 48
            //CardCrawlGame.metricData.addCampfireChoiceData("PURGE", card.getMetricID());// 49
            CardCrawlGame.sound.play("CARD_EXHAUST");// 50
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(card, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));// 51
            AbstractDungeon.player.masterDeck.removeCard(card);// 52
            AbstractDungeon.gridSelectScreen.selectedCards.clear();// 53
        }

        if (this.duration < 1.0F && !this.openedScreen) {// 57
            this.openedScreen = true;// 58
            AbstractDungeon.gridSelectScreen.open(TrinketLibrary.getPlayersTrinkets(AbstractDungeon.player), 1, "Choose a trinket to remove from your deck.", false, false, true, true);// 59 60
        }

        if (this.duration < 0.0F) {// 70
            this.isDone = true;// 71
            if (CampfireUI.hidden) {// 72
                AbstractRoom.waitTimer = 0.0F;// 73
                ((RestRoom)AbstractDungeon.getCurrRoom()).campfireUI.reopen();// 74
                //((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();// 75
            }
        }

    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {// 84
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);// 85
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);// 87
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);// 93
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);// 94
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {// 96
            AbstractDungeon.gridSelectScreen.render(sb);// 97
        }

    }

    public void dispose() {
    }
}
