package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.RockTypes;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

public class MiningState implements State {

    private final int[] COPPER_ID = { 7484, 7453 };
    private final int[] TIN_ID = { 7486, 7485 };
    private final int[] CLAY_ID = { 7454, 7487 };
    private final int[] COAL_ID = { 7456, 7489 };
    private final int[] IRON_ID = { 7455, 7488 };
    private final int[] SILVER_ID = { 7457, 7490 };
    private final int[] GOLD_ID = { 7491, 7458 };
    private final int[] MITHRIL_ID = { 7492, 7459 };

    private boolean interacting = false;
    private Tile rockTile;
    private int objectId;
    private GameObject rock = null;

    private static int standingStill;

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("MINING");

        if (!interacting && rock == null) {
            RockTypes chosenRock = GlobalSettings.CHOSEN_ROCK_TYPES.get(Calculations.random(GlobalSettings.CHOSEN_ROCK_TYPES.size()));
            int workingRadius = GlobalSettings.WORKING_RADIUS;
            int[] mineName;

            switch (chosenRock) {
                case Copper:
                    mineName = COPPER_ID;
                    break;
                case Tin:
                    mineName = TIN_ID;
                    break;
                case Clay:
                    mineName = CLAY_ID;
                    break;
                case Coal:
                    mineName = COAL_ID;
                    break;
                case Iron:
                    mineName = IRON_ID;
                    break;
                case Silver:
                    mineName = SILVER_ID;
                    break;
                case Gold:
                    mineName = GOLD_ID;
                    break;
                case Mithril:
                    mineName = MITHRIL_ID;
                    break;
                default:
                    mineName = TIN_ID;
            }

            if (workingRadius > 0) {
                rock = context.getGameObjects().closest(object -> object.getName().equals("Rocks") && isIn(object.getID(), mineName) && context.getMap().canReach(object) && GlobalSettings.START_TILE.distance(object) < workingRadius);
            } else {
                rock = context.getGameObjects().closest(object -> object.getName().equals("Rocks") && isIn(object.getID(), mineName) && context.getMap().canReach(object));
            }
        }

        if (rock != null) {
            if (!interacting && rock.interact()) {
                AbstractScript.sleep(700, 1200);
                interacting = true;
                rockTile = rock.getTile();
                objectId = rock.getID();
                AbstractScript.log("Interacting with " + rock.getName());
            }

            int count = 0;
            GameObject[] objects = context.getGameObjects().getObjectsOnTile(rockTile);
            for (GameObject object : objects) {
                if (object.getID() != objectId) {
                    count++;
                }
            }

            if (count == objects.length) {
                return true;
            }
        }

        antibanManager.runFeatures();

        if (isStandingStill(context)) {
            interacting = false;
            rock = null;
            AbstractScript.log("Standing still...");
        }

        return false;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }

    private boolean isIn(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (num == arr[i]) {
                return true;
            }
        }

        return false;
    }

    private boolean isStandingStill(AbstractScript context) {

        if (!context.getLocalPlayer().isAnimating()) {
            standingStill++;
        } else {
            standingStill = 0;
        }

        return standingStill > 35;
    }
}
