package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjabots.core.Utilities;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.script.AbstractScript;

public class WalkFromBankState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("WALK_FROM_BANK");

        if (!(GlobalSettings.SOURCE_TILE.distance(context.getLocalPlayer().getTile()) < 2)) {
            Utilities.GoToTile(context, GlobalSettings.SOURCE_TILE);
            return false;
        }
        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
