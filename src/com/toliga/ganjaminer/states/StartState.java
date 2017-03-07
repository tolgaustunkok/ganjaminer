package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import org.dreambot.api.script.AbstractScript;

public class StartState implements State {

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        context.getSkillTracker().start();

        antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setProbability(GlobalSettings.CAMERA_PROBABILTY);
        antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setProbability(GlobalSettings.MOUSE_PROBABILTY);
        antibanManager.getFeature("RANDOM_TAB_CHECKING").setProbability(GlobalSettings.TAB_PROBABILTY);

        if (GlobalSettings.ENABLE_CAMERA) {
            antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setEnabled(true);
        } else {
            antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setEnabled(false);
        }

        if (GlobalSettings.ENABLE_MOUSE) {
            antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setEnabled(true);
        } else {
            antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setEnabled(false);
        }

        if (GlobalSettings.ENABLE_TAB) {
            antibanManager.getFeature("RANDOM_TAB_CHECKING").setEnabled(true);
        } else {
            antibanManager.getFeature("RANDOM_TAB_CHECKING").setEnabled(false);
        }

        return true;
    }

    @Override
    public State next() {
        return new CheckInventoryState();
    }
}
