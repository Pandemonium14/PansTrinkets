package pansTrinkets.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;



public abstract class TrinketPower extends AbstractPower {

    public boolean amountInDescription = false;
    public String[] descriptions;

    public void updateDescription(String[] desc) {

        if (amountInDescription && amount > 1) {
            description = desc[1] + amount + desc[2];
        } else {
            description = desc[0];
        }
    }

    @Override
    public void updateDescription() {
        updateDescription(descriptions);
    }
}
