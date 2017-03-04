package com.toliga.ganjaminer;

import com.toliga.ganjabots.path.PathProfile;
import com.toliga.ganjaminer.models.RockTypes;
import org.dreambot.api.methods.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class GlobalSettings {
    public static final String SAVE_LOCATION = System.getProperty("user.home") + "\\DreamBot\\Scripts\\GanjaMiner\\";

    public static Tile SOURCE_TILE = null;

    public static boolean DEBUG = true;
    public static boolean BANK_WHEN_FULL = true;
    public static boolean USE_PATH_CREATOR = false;
    public static boolean DEPOSIT_GEMS = false;

    public static PathProfile CHOSEN_BANK_GO_PROFILE = null;
    public static PathProfile CHOSEN_BANK_RETURN_PROFILE = null;

    public static Tile START_TILE = null;
    public static int WORKING_RADIUS = -1;

    public static List<RockTypes> CHOSEN_ROCK_TYPES = new ArrayList<>();

}
