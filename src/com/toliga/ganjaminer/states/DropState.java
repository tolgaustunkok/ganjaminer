package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.script.AbstractScript;

public class DropState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("DROP");

        context.getInventory().dropAllExcept(item -> !item.getName().contains("ore"));
        AbstractScript.sleepUntil(() -> context.getInventory().isEmpty(), 50000);

        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
