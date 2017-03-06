package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import org.dreambot.api.script.AbstractScript;

public class StartState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        context.getSkillTracker().start();
        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
