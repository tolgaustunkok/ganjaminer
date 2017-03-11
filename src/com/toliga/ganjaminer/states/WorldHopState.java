package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.script.AbstractScript;

public class WorldHopState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("WORLD_HOP");

        if (GlobalSettings.WORLD_HOP) {
            World chosenWorld = context.getWorlds().getRandomWorld(world -> {
                int playerTotalLevel = 0;
                int[] levels = context.getClient().getLevels();

                for (int level : levels) {
                    playerTotalLevel += level;
                }
                return (GlobalSettings.IS_MEMBER ? world.isMembers() : world.isF2P())
                        && (world.getMinimumLevel() < playerTotalLevel)
                        && !world.isPVP()
                        && !world.isDeadmanMode()
                        && !world.isHighRisk();
            });

            context.getWorldHopper().hopWorld(chosenWorld);
            AbstractScript.sleep(3500, 4000);
        }

        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
