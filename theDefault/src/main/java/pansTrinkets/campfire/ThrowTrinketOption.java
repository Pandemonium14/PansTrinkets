package pansTrinkets.campfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import pansTrinkets.helpers.TrinketLibrary;

import static pansTrinkets.DefaultMod.getModID;

public class ThrowTrinketOption extends AbstractCampfireOption {

    public static UIStrings strings = CardCrawlGame.languagePack.getUIString("pansTrinkets:CampfireOption");

    public ThrowTrinketOption() {
        this.label = strings.TEXT[0];
        this.description = strings.TEXT[1];
        this.usable = TrinketLibrary.getPlayersTrinkets(AbstractDungeon.player).size() > 0;
        this.img = new Texture(Gdx.files.internal(getModID() + "Resources/images/icon/trinketcampfire.png"));
    }

    @Override
    public void useOption() {
        if (this.usable) {// 22
            AbstractDungeon.effectList.add(new ThrowTrinketEffect());// 23
        }
    }
}
