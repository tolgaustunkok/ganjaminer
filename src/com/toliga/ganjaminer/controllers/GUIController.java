package com.toliga.ganjaminer.controllers;

import com.toliga.ganjabots.core.Feedback;
import com.toliga.ganjabots.core.SaveManager;
import com.toliga.ganjabots.path.PathProfile;
import com.toliga.ganjaminer.GanjaMinerMain;
import com.toliga.ganjaminer.GlobalSettings;
import com.toliga.ganjaminer.models.GUIModel;
import com.toliga.ganjaminer.models.RockTypes;
import com.toliga.ganjaminer.views.BotGUI;
import com.toliga.ganjaminer.views.PathFinderGUI;
import org.dreambot.api.script.AbstractScript;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.List;

public class GUIController {

    private GUIModel guiModel;
    private BotGUI view;
    private AbstractScript context;
    private SaveManager saveManager;

    public GUIController(AbstractScript context, GUIModel GUIModelModel, BotGUI view) {
        this.guiModel = GUIModelModel;
        this.view = view;
        this.context = context;

        init();
    }

    private void init() {
        view.getAddButton().addActionListener(this::add);
        view.getBtnStart().addActionListener(this::start);
        view.getBtnStop().addActionListener(this::stop);
        view.getRemoveButton().addActionListener(this::remove);
        view.getBankTypeComboBox().addActionListener(this::bankComboBox);
        view.getBtnCreatePath().addActionListener(this::createPath);
        view.getBtnSubmit().addActionListener(this::submitFeedback);
        view.getCameraSlider().addChangeListener(this::cameraSliderChanged);
        view.getMouseSlider().addChangeListener(this::mouseSliderChanged);
        view.getTabCheckingSlider().addChangeListener(this::tabCheckingSliderChanged);

        saveManager = new SaveManager(GlobalSettings.SAVE_LOCATION + "save.xml");

        List<PathProfile> pathProfiles = saveManager.loadProfiles();

        if (pathProfiles != null) {
            for (PathProfile pathProfile : pathProfiles) {
                AbstractScript.log(pathProfile.getName());
                if (pathProfile.getName().contains("to")) {
                    ((DefaultListModel<PathProfile>) view.getListWalkToBank().getModel()).addElement(pathProfile);
                } else if (pathProfile.getName().contains("from")) {
                    ((DefaultListModel<PathProfile>) view.getListWalkFromBank().getModel()).addElement(pathProfile);
                }
            }
        }
    }

    private void tabCheckingSliderChanged(ChangeEvent changeEvent) {
        GlobalSettings.TAB_PROBABILTY = view.getTabCheckingSlider().getValue() / 3000.0f;
    }

    private void mouseSliderChanged(ChangeEvent changeEvent) {
        GlobalSettings.MOUSE_PROBABILTY = view.getMouseSlider().getValue() / 1000.0f;
    }

    private void cameraSliderChanged(ChangeEvent changeEvent) {
        GlobalSettings.CAMERA_PROBABILTY = view.getCameraSlider().getValue() / 1000.0f;
    }

    private void bankComboBox(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();

        if (source.getSelectedIndex() == 0) {
            guiModel.setBank(true);
        } else {
            guiModel.setBank(false);
        }
    }

    private void start(ActionEvent event) {
        AbstractScript.log("Started");

        GlobalSettings.DEPOSIT_GEMS = view.getChckbxDepositGems().isSelected();
        GlobalSettings.USE_PATH_CREATOR = view.getChckbxUsePathCreator().isSelected();

        if (GlobalSettings.USE_PATH_CREATOR) {
            int selectedIndex = view.getListWalkToBank().getSelectedIndex();
            GlobalSettings.CHOSEN_BANK_GO_PROFILE = (PathProfile) view.getListWalkToBank().getModel().getElementAt(selectedIndex);

            selectedIndex = view.getListWalkFromBank().getSelectedIndex();
            GlobalSettings.CHOSEN_BANK_RETURN_PROFILE = (PathProfile) view.getListWalkFromBank().getModel().getElementAt(selectedIndex);
        }

        if ((int) view.getWorkingRadiusSpinner().getValue() > 0) {
            GlobalSettings.START_TILE = context.getLocalPlayer().getTile();
            GlobalSettings.WORKING_RADIUS = (int) view.getWorkingRadiusSpinner().getValue();
        }

        ((GanjaMinerMain) context).setStarted(true);
        GlobalSettings.ENABLE_CAMERA = view.getChckbxEnableCamera().isSelected();
        GlobalSettings.ENABLE_MOUSE = view.getChckbxEnableMouse().isSelected();
        GlobalSettings.ENABLE_TAB = view.getChckbxEnableTabChecking().isSelected();
        GlobalSettings.MANNERS = view.getChckbxManners().isSelected();

        view.getBtnStart().setEnabled(false);
        view.getBtnStop().setEnabled(true);
    }

    private void stop(ActionEvent event) {
        ((GanjaMinerMain) context).setStarted(false);
        ((GanjaMinerMain) context).reset();
        view.getBtnStart().setEnabled(true);
        view.getBtnStop().setEnabled(false);
    }

    private void add(ActionEvent event) {
        RockTypes chosenRockType = (RockTypes) view.getMineTypeComboBox().getSelectedItem();
        ((DefaultListModel<String>)(view.getMineTypesList().getModel())).addElement(chosenRockType.name());
        guiModel.addChosenRockType(chosenRockType);
    }

    private void remove(ActionEvent event) {
        ((DefaultListModel<String>)(view.getMineTypesList().getModel())).remove(view.getMineTypesList().getSelectedIndex());
        guiModel.removeChosenRockType((RockTypes) view.getMineTypesList().getSelectedValue());
    }

    private void createPath(ActionEvent event) {
        if (!view.getProfileNameTextField().getText().isEmpty()) {
            PathProfile toProfile = new PathProfile(view.getProfileNameTextField().getText() + "_to");
            PathProfile fromProfile = new PathProfile(view.getProfileNameTextField().getText() + "_from");

            PathFinderGUI pathFinderGUI = new PathFinderGUI(context, toProfile, fromProfile, saveManager);
            pathFinderGUI.setVisible(true);

            ((DefaultListModel<PathProfile>) view.getListWalkToBank().getModel()).addElement(toProfile);
            ((DefaultListModel<PathProfile>) view.getListWalkFromBank().getModel()).addElement(fromProfile);
        } else {
            JOptionPane.showMessageDialog(view, "Please enter a name for the profile.", "Ganja Miner", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void submitFeedback(ActionEvent event) {
        Feedback feedback = new Feedback();
        feedback.SendString(view.getFeedbackTextArea().getText(), "unknown", "R2FuamEgTWluZXI=");
    }
}
