package pansTrinkets.campfire;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import pansTrinkets.helpers.TrinketLibrary;

import static pansTrinkets.DefaultMod.getModID;

public class ThrowTrinketOption extends AbstractCampfireOption {

    public ThrowTrinketOption() {
        this.label = "Leave a trinket behind";
        this.description = "Remove a trinket from your deck. Free action.";
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
