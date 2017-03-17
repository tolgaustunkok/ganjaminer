package com.toliga.ganjaminer;

import com.toliga.ganjabots.core.StateScheduler;
import com.toliga.ganjabots.graphics.InGameGUIBuilder;
import com.toliga.ganjaminer.controllers.GUIController;
import com.toliga.ganjaminer.drawables.MiningDrawable;
import com.toliga.ganjaminer.models.GUIModel;
import com.toliga.ganjaminer.states.StartState;
import com.toliga.ganjaminer.views.BotGUI;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

import java.awt.*;

@ScriptManifest(author = "GanjaSmuggler", category = Category.MINING, name = "Ganja Miner", description = "Customizable mining script with various rock types and no location limitation. Banking, powermining, gem depositing and user-defined path creating features are supported.", version = 1.0)
public class GanjaMinerMain extends AbstractScript {

    public static final String VERSION = "0.3.1";
    private boolean started = false;
    private StateScheduler stateScheduler;
    private GanjaMinerAntibanManager antibanManager;
    private InGameGUIBuilder guiBuilder;
    private BotGUI view;

    @Override
    public void onStart() {
        stateScheduler = new StateScheduler(this, new StartState());
        antibanManager = new GanjaMinerAntibanManager(this);
        guiBuilder = new InGameGUIBuilder(this, "Ganja Miner", VERSION, new Color(155, 123, 180), new MiningDrawable(this));

        view = new BotGUI();
        new GUIController(this, view);

        antibanManager.addFeature("RANDOM_CAMERA_ROTATION");
        antibanManager.addFeature("RANDOM_MOUSE_MOVEMENT");
        antibanManager.addFeature("RANDOM_TAB_CHECKING");

        antibanManager.disableAllFeatures();

        guiBuilder.setCanDraw(true);
        view.setVisible(true);
    }

    @Override
    public int onLoop() {
        if (started) {
            stateScheduler.executeState(antibanManager);
        }
        return Calculations.random(50, 100);
    }

    @Override
    public void onPaint(Graphics graphics) {
        if (started) {
            guiBuilder.draw((Graphics2D) graphics);
        }
    }

    @Override
    public void onExit() {
        view.setVisible(false);
        view.dispose();
    }

    public void reset() {
        stateScheduler = new StateScheduler(this, new StartState());
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }
}
