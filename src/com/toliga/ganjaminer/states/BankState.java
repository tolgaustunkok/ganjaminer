package com.toliga.ganjaminer.states;

import com.toliga.ganjabots.core.AntibanManager;
import com.toliga.ganjabots.core.State;
import com.toliga.ganjabots.core.Utilities;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;

public class BankState implements State {

    private int itemIndex = 0;
    private boolean finishedItem = false;
    private GUIModel model;

    public BankState() {
        this.model = GUIModel.getInstance();
    }

    @Override
    public boolean execute(AbstractScript context, AntibanManager antibanManager) {
        if (GlobalSettings.DEBUG) AbstractScript.log("BANK");
        Utilities.OpenTab(context, Tab.INVENTORY);

        if (!context.getBank().isOpen() && context.getBank().open()) {
            AbstractScript.sleepUntil(() -> context.getBank().isOpen(), 9000);
        }

        if (context.getBank().isOpen()) {
            if (model.isDepositGems() && context.getInventory().contains(item -> item.getName().contains("Uncut"))) {
                context.getBank().depositAll(item -> item.getName().contains("Uncut"));
                AbstractScript.sleepUntil(() -> context.getInventory().count(item -> item.getName().contains("Uncut")) == 0, 10000);
            }

            if (itemIndex < model.getChosenRockTypes().size()) {
                String itemName = model.getChosenRockTypes().get(itemIndex).name();
                AbstractScript.log("Item name: " + itemName + " ore");
                context.getBank().depositAll(itemName.equals("Clay") || itemName.equals("Coal") ? itemName : itemName + " ore");
                AbstractScript.sleepUntil(() -> !context.getInventory().contains(itemName), 3000);
                itemIndex++;
            } else {
                finishedItem = true;
            }
        }

        if (finishedItem) {
            if (context.getBank().close()) {
                AbstractScript.sleepUntil(() -> !context.getBank().isOpen(), 8000);
                return true;
            }
        }

        return false;
    }

    @Override
    public State next() {
        if (model.isUsePathCreator()) {
            return new WalkFromBankWithGuidanceState();
        }
        return new WalkFromBankState();
    }
}
