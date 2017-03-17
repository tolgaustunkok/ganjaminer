package com.toliga.ganjaminer.models;

import com.toliga.ganjabots.path.PathProfile;
import org.dreambot.api.methods.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class GUIModel {
    private static GUIModel instance = null;

    private Tile sourceTile = null;
    private Tile startTile = null;

    private boolean bankWhenFull = true;
    private boolean usePathCreator = false;
    private boolean depositGems = false;
    private boolean enableCamera = false;
    private boolean enableMouse = false;
    private boolean enableTab = false;
    private boolean manners = false;
    private boolean worldHop = false;
    private boolean isMember = false;

    private float cameraProbability = 0.5f;
    private float mouseProbability = 0.5f;
    private float tabProbability = 0.5f;

    private PathProfile chosenBankGoProfile = null;
    private PathProfile chosenBankReturnProfile = null;

    private int workingRadius = -1;

    private List<RockTypes> chosenRockTypes = new ArrayList<>();

    private GUIModel() {}

    public static GUIModel getInstance() {
        if (instance == null) {
            instance = new GUIModel();
        }

        return instance;
    }

    public void addChosenRockType(RockTypes chosenRockType) {
        chosenRockTypes.add(chosenRockType);
    }

    public void removeChosenRockType(int index) {
        chosenRockTypes.remove(index);
    }

    public Tile getSourceTile() {
        return sourceTile;
    }

    public void setSourceTile(Tile sourceTile) {
        this.sourceTile = sourceTile;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isBankWhenFull() {
        return bankWhenFull;
    }

    public void setBankWhenFull(boolean bankWhenFull) {
        this.bankWhenFull = bankWhenFull;
    }

    public boolean isUsePathCreator() {
        return usePathCreator;
    }

    public void setUsePathCreator(boolean usePathCreator) {
        this.usePathCreator = usePathCreator;
    }

    public boolean isDepositGems() {
        return depositGems;
    }

    public void setDepositGems(boolean depositGems) {
        this.depositGems = depositGems;
    }

    public boolean isEnableCamera() {
        return enableCamera;
    }

    public void setEnableCamera(boolean enableCamera) {
        this.enableCamera = enableCamera;
    }

    public boolean isEnableMouse() {
        return enableMouse;
    }

    public void setEnableMouse(boolean enableMouse) {
        this.enableMouse = enableMouse;
    }

    public boolean isEnableTab() {
        return enableTab;
    }

    public void setEnableTab(boolean enableTab) {
        this.enableTab = enableTab;
    }

    public boolean isManners() {
        return manners;
    }

    public void setManners(boolean manners) {
        this.manners = manners;
    }

    public boolean isWorldHop() {
        return worldHop;
    }

    public void setWorldHop(boolean worldHop) {
        this.worldHop = worldHop;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public float getCameraProbability() {
        return cameraProbability;
    }

    public void setCameraProbability(float cameraProbability) {
        this.cameraProbability = cameraProbability;
    }

    public float getMouseProbability() {
        return mouseProbability;
    }

    public void setMouseProbability(float mouseProbability) {
        this.mouseProbability = mouseProbability;
    }

    public float getTabProbability() {
        return tabProbability;
    }

    public void setTabProbability(float tabProbability) {
        this.tabProbability = tabProbability;
    }

    public PathProfile getChosenBankGoProfile() {
        return chosenBankGoProfile;
    }

    public void setChosenBankGoProfile(PathProfile chosenBankGoProfile) {
        this.chosenBankGoProfile = chosenBankGoProfile;
    }

    public PathProfile getChosenBankReturnProfile() {
        return chosenBankReturnProfile;
    }

    public void setChosenBankReturnProfile(PathProfile chosenBankReturnProfile) {
        this.chosenBankReturnProfile = chosenBankReturnProfile;
    }

    public int getWorkingRadius() {
        return workingRadius;
    }

    public void setWorkingRadius(int workingRadius) {
        this.workingRadius = workingRadius;
    }

    public List<RockTypes> getChosenRockTypes() {
        return chosenRockTypes;
    }
}
