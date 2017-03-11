package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.script.AbstractScript;

public class CheckInventoryState implements State {

    private State nextState;

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("CHECK_INVENTORY");

        if (context.getInventory().isFull()) {
            if (GlobalSettings.BANK_WHEN_FULL) {
                GlobalSettings.SOURCE_TILE = context.getLocalPlayer().getTile();
                if (GlobalSettings.USE_PATH_CREATOR) {
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
