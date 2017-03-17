package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import org.dreambot.api.script.AbstractScript;

public class DropState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("DROP");

        if (!GUIModel.getInstance().isDepositGems()) {
            context.getInventory().dropAll(item -> item.getName().startsWith("Uncut"));
        }
        context.getInventory().dropAll(item -> item.getName().equals("Clay") || item.getName().equals("Coal") || item.getName().contains("ore"));
        AbstractScript.sleepUntil(() -> !context.getInventory().contains(item -> item.getName().equals("Clay") || item.getName().equals("Coal") || item.getName().contains("ore")), 50000);

        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
