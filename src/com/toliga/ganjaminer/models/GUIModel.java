package com.toliga.ganjaminer.models;

import com.toliga.ganjaminer.GlobalSettings;

public class GUIModel {
    public void setBank(boolean bank) {
        GlobalSettings.BANK_WHEN_FULL = bank;
    }

    public void addChosenRockType(RockTypes chosenRockType) {
        GlobalSettings.CHOSEN_ROCK_TYPES.add(chosenRockType);
    }

    public void removeChosenRockType(int index) {
        GlobalSettings.CHOSEN_ROCK_TYPES.remove(index);
    }
}
