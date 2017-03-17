package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import org.dreambot.api.script.AbstractScript;

public class StartState implements State {

    private GUIModel model = GUIModel.getInstance();

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        context.getSkillTracker().start();

        antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setProbability(model.getCameraProbability());
        antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setProbability(model.getMouseProbability());
        antibanManager.getFeature("RANDOM_TAB_CHECKING").setProbability(model.getTabProbability());

        if (model.isEnableCamera()) {
            antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setEnabled(true);
        } else {
            antibanManager.getFeature("RANDOM_CAMERA_ROTATION").setEnabled(false);
        }

        if (model.isEnableMouse()) {
            antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setEnabled(true);
        } else {
            antibanManager.getFeature("RANDOM_MOUSE_MOVEMENT").setEnabled(false);
        }

        if (model.isEnableTab()) {
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
