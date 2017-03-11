package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.script.AbstractScript;

import java.util.Collections;
import java.util.List;

public class WorldHopState implements State {

    private static int worldIndex = 0;
    private static List<World> chosenWorlds = null;

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("WORLD_HOP");

        if (GlobalSettings.WORLD_HOP) {
            if (chosenWorlds == null) {
                chosenWorlds = context.getWorlds().all(world -> (GlobalSettings.IS_MEMBER ? world.isMembers() : world.isF2P())
                            && (context.getSkills().getTotalLevel() >= world.getMinimumLevel())
                            && !world.isPVP()
                            && !world.isDeadmanMode()
                            && !world.isHighRisk()
                );
                sortWorlds(chosenWorlds);
            }

            context.getWorldHopper().hopWorld(chosenWorlds.get(worldIndex++));
            if (worldIndex >= chosenWorlds.size()) {
                worldIndex = 0;
            }
            AbstractScript.sleep(3500, 4000);
        }

        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }

    private void sortWorlds(List<World> worlds) {
        Collections.sort(worlds, (o1, o2) -> {
            if (o1.getRealID() > o2.getRealID()) {
                return 1;
            } else if (o1.getRealID() < o2.getRealID()) {
                return -1;
            } else {
                return 0;
            }
        });
    }
}
