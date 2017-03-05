package com.toliga.ganjaminer;

import com.toliga.ganjabots.core.StateScheduler;
import com.toliga.ganjabots.graphics.InGameGUIBuilder;
import com.toliga.ganjaminer.controllers.GUIController;
import com.toliga.ganjaminer.drawables.MiningDrawable;
import com.toliga.ganjaminer.models.GUIModel;
import com.toliga.ganjaminer.states.CheckInventoryState;
import com.toliga.ganjaminer.views.BotGUI;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(author = "GanjaSmuggler", category = Category.MINING, name = "Ganja Miner", description = "Ganja Miner description will be here.", version = 1.0)
public class GanjaMinerMain extends AbstractScript {

    public static final String VERSION = "0.1.0";
    public static boolean STARTED = false;
    private StateScheduler stateScheduler;
    private GanjaMinerAntibanManager antibanManager;
    private InGameGUIBuilder guiBuilder;
    private BotGUI view;

    @Override
    public void onStart() {
        stateScheduler = new StateScheduler(this, new CheckInventoryState());
        antibanManager = new GanjaMinerAntibanManager(this);
        guiBuilder = new InGameGUIBuilder(this, "Ganja Miner", VERSION, new Color(155, 123, 180), new MiningDrawable(this));

        view = new BotGUI();
        GUIModel guiModel = new GUIModel();
        new GUIController(this, guiModel, view);

        antibanManager.createFeature("RANDOM_CAMERA_ROTATION");
        antibanManager.createFeature("RANDOM_MOUSE_MOVEMENT");
        antibanManager.createFeature("RANDOM_TAB_CHECKING");

        antibanManager.disableAllFeatures();

        getSkillTracker().start();
        guiBuilder.setCanDraw(true);
        view.setVisible(true);
    }

    @Override
    public int onLoop() {
        if (STARTED) {
            stateScheduler.executeState(antibanManager);
        }
        return Calculations.random(50, 100);
    }

    @Override
    public void onPaint(Graphics graphics) {
        if (STARTED) {
            guiBuilder.draw((Graphics2D) graphics);
        }
    }

    @Override
    public void onExit() {
        view.setVisible(false);
        view.dispose();
    }
}
