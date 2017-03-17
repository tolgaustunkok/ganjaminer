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

    private GUIModel model;
    private BotGUI view;
    private AbstractScript context;
    private SaveManager saveManager;

    public GUIController(AbstractScript context, BotGUI view) {
        this.model = GUIModel.getInstance();
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
        model.setTabProbability(view.getTabCheckingSlider().getValue() / 4000.0f);
    }

    private void mouseSliderChanged(ChangeEvent changeEvent) {
        model.setMouseProbability(view.getMouseSlider().getValue() / 2000.0f);
    }

    private void cameraSliderChanged(ChangeEvent changeEvent) {
        model.setCameraProbability(view.getCameraSlider().getValue() / 2000.0f);
    }

    private void bankComboBox(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();

        if (source.getSelectedIndex() == 0) {
            model.setBankWhenFull(true);
        } else if (source.getSelectedIndex() == 1) {
            model.setBankWhenFull(false);
        } else if (source.getSelectedIndex() == 2) {
            model.setBankWhenFull(true);
            model.setWorldHop(true);
        }
    }

    private void start(ActionEvent event) {
        AbstractScript.log("Started");

        model.setDepositGems(view.getChckbxDepositGems().isSelected());
        model.setUsePathCreator(view.getChckbxUsePathCreator().isSelected());

        if (model.isUsePathCreator()) {
            int selectedIndex = view.getListWalkToBank().getSelectedIndex();
            model.setChosenBankGoProfile((PathProfile) view.getListWalkToBank().getModel().getElementAt(selectedIndex));

            selectedIndex = view.getListWalkFromBank().getSelectedIndex();
            model.setChosenBankReturnProfile((PathProfile) view.getListWalkFromBank().getModel().getElementAt(selectedIndex));
        }

        if ((int) view.getWorkingRadiusSpinner().getValue() > 0) {
            model.setStartTile(context.getLocalPlayer().getTile());
            model.setWorkingRadius((int) view.getWorkingRadiusSpinner().getValue());
        }

        ((GanjaMinerMain) context).setStarted(true);
        model.setEnableCamera(view.getChckbxEnableCamera().isSelected());
        model.setEnableMouse(view.getChckbxEnableMouse().isSelected());
        model.setEnableTab(view.getChckbxEnableTabChecking().isSelected());
        model.setManners(view.getChckbxManners().isSelected());
        model.setMember(view.getChckbxMember().isSelected());

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
        model.addChosenRockType(chosenRockType);
    }

    private void remove(ActionEvent event) {
        model.removeChosenRockType(view.getMineTypesList().getSelectedIndex());
        ((DefaultListModel<String>)(view.getMineTypesList().getModel())).remove(view.getMineTypesList().getSelectedIndex());
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
