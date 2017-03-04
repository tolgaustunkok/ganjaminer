package com.toliga.ganjaminer;

import com.toliga.ganjabots.core.AntibanFeature;
import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjaminer.antiban.RandomCameraRotation;
import com.toliga.ganjaminer.antiban.RandomMouseMovement;
import com.toliga.ganjaminer.antiban.RandomTabChecking;
import org.dreambot.api.script.AbstractScript;

public class GanjaMinerAntibanManager extends AntibanManager {
    public GanjaMinerAntibanManager(AbstractScript context) {
        super(context);
    }

    @Override
    protected AntibanFeature createFeature(String name) {
        AntibanFeature feature = null;

        if (name.equals("RANDOM_CAMERA_ROTATION")) {
            feature = new RandomCameraRotation(0.5f);
        } else if (name.equals("RANDOM_MOUSE_MOVEMENT")) {
            feature = new RandomMouseMovement(0.5f);
        } else if (name.equals("RANDOM_TAB_CHECKING")) {
            feature = new RandomTabChecking(0.5f);
        }

        return feature;
    }
}
