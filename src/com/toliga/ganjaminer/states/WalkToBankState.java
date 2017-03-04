package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjabots.core.Utilities;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;

public class WalkToBankState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("WALK_TO_BANK");

        Area destinationArea = context.getBank().getClosestBankLocation().getArea(1);

        if (!destinationArea.contains(context.getLocalPlayer())) {
            Utilities.GoToArea(context, destinationArea);
            return false;
        }

        return true;
    }

    @Override
    public State next() {
        return new BankState();
    }
}
