package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import org.dreambot.api.script.AbstractScript;

public class CheckInventoryState implements State {

    private State nextState;
    private GUIModel model = GUIModel.getInstance();

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("CHECK_INVENTORY");

        if (context.getInventory().isFull()) {
            if (model.isBankWhenFull()) {
                model.setSourceTile(context.getLocalPlayer().getTile());
                if (model.isUsePathCreator()) {
                    nextState = new WalkToBankWithGuidanceState();
                } else {
                    nextState = new WalkToBankState();
                }
            } else {
                nextState = new DropState();
            }
        } else {
            nextState = new MiningState();
        }

        return true;
    }

    @Override
    public State next() {
        return nextState;
    }
}
