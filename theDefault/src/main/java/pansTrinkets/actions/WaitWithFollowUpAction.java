package pansTrinkets.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class WaitWithFollowUpAction extends AbstractGameAction {

    private AbstractGameAction followUp;
    private int waitFor;

    public WaitWithFollowUpAction(AbstractGameAction action) {
        followUp = action;
        waitFor = 1;
    }

    public WaitWithFollowUpAction(AbstractGameAction action, int waitFor) {
        followUp = action;
        this.waitFor = waitFor;
    }

    @Override
    public void update() {
        if (waitFor == 1) {
            addToBot(followUp);
        } else {
            addToBot(new WaitWithFollowUpAction(followUp, waitFor - 1 ));
        }
        isDone = true;
    }
}
