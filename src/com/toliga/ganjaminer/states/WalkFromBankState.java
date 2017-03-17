package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjabots.core.Utilities;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import org.dreambot.api.script.AbstractScript;

public class WalkFromBankState implements State {

    private GUIModel model = GUIModel.getInstance();

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("WALK_FROM_BANK");

        if (!(model.getSourceTile().distance(context.getLocalPlayer().getTile()) < 2)) {
            Utilities.GoToTile(context, model.getSourceTile());
            return false;
        }
        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
