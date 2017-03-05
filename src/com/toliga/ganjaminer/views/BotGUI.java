package com.toliga.ganjaminer.views;

import com.toliga.ganjabots.path.PathProfile;
import com.toliga.ganjaminer.models.RockTypes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BotGUI extends JFrame {
    private JButton btnStart;
    private JButton btnStop;
    private JComboBox bankTypeComboBox;
    private JComboBox mineTypeComboBox;
    private JButton addButton;
    private JButton removeButton;
    private JCheckBox chckbxDepositGems;
    private JCheckBox chckbxSuperHeat;
    private JComboBox comboBox;
    private JCheckBox chckbxHighAlch;
    private JTextField textField;
    private JList mineTypesList;
    private JList listWalkToBank;
    private JList listWalkFromBank;
    private JButton btnCreatePath;
    private JTextField profileNameTextField;
    private JSpinner workingRadiusSpinner;
    private JCheckBox chckbxUsePathCreator;
    private JSlider tabCheckingSlider;
    private JSlider cameraSlider;
    private JSlider mouseSlider;
    private JTextArea feedbackTextArea;
    private JButton btnSubmit;

    private JPanel contentPane;

    public BotGUI() {
        setTitle("Ganja Miner");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 460, 381);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        btnStart = new JButton("Start");
        panel.add(btnStart);

        btnStop = new JButton("Stop");
        btnStop.setEnabled(false);
        panel.add(btnStop);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        panel_1.add(tabbedPane);

        JPanel settingsTab = new JPanel();
        settingsTab.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.addTab("Settings", null, settingsTab, null);
        settingsTab.setLayout(new BorderLayout(10, 10));

        JPanel panel_2 = new JPanel();
        settingsTab.add(panel_2, BorderLayout.NORTH);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{182, 182, 0};
        gbl_panel_2.rowHeights = new int[]{33, 33, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);

        bankTypeComboBox = new JComboBox();
        bankTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Bank", "Powermine"}));
        GridBagConstraints gbc_bankTypeComboBox = new GridBagConstraints();
        gbc_bankTypeComboBox.fill = GridBagConstraints.BOTH;
        gbc_bankTypeComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_bankTypeComboBox.gridx = 0;
        gbc_bankTypeComboBox.gridy = 0;
        panel_2.add(bankTypeComboBox, gbc_bankTypeComboBox);

        chckbxUsePathCreator = new JCheckBox("Use path creator");
        GridBagConstraints gbc_chckbxUsePathCreator = new GridBagConstraints();
        gbc_chckbxUsePathCreator.anchor = GridBagConstraints.WEST;
        gbc_chckbxUsePathCreator.insets = new Insets(0, 0, 5, 0);
        gbc_chckbxUsePathCreator.gridx = 1;
        gbc_chckbxUsePathCreator.gridy = 0;
        panel_2.add(chckbxUsePathCreator, gbc_chckbxUsePathCreator);

        JPanel panel_5 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 1;
        gbc_panel_5.gridy = 2;
        panel_2.add(panel_5, gbc_panel_5);

        JLabel lblNewLabel = new JLabel("Working radius:");
        panel_5.add(lblNewLabel);

        workingRadiusSpinner = new JSpinner();
        workingRadiusSpinner.setPreferredSize(new Dimension(50, 20));
        workingRadiusSpinner.setModel(new SpinnerNumberModel(-1, -100, 100, 1));
        panel_5.add(workingRadiusSpinner);

        JLabel lblTiles = new JLabel("tiles");
        panel_5.add(lblTiles);

        mineTypeComboBox = new JComboBox();
        mineTypeComboBox.setModel(new DefaultComboBoxModel(RockTypes.values()));
        GridBagConstraints gbc_mineTypeComboBox = new GridBagConstraints();
        gbc_mineTypeComboBox.fill = GridBagConstraints.BOTH;
        gbc_mineTypeComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_mineTypeComboBox.gridx = 0;
        gbc_mineTypeComboBox.gridy = 1;
        panel_2.add(mineTypeComboBox, gbc_mineTypeComboBox);

        JPanel panel_4 = new JPanel();
        GridBagConstraints gbc_panel_4 = new GridBagConstraints();
        gbc_panel_4.insets = new Insets(0, 0, 5, 0);
        gbc_panel_4.fill = GridBagConstraints.BOTH;
        gbc_panel_4.gridx = 1;
        gbc_panel_4.gridy = 1;
        panel_2.add(panel_4, gbc_panel_4);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 23));
        panel_4.add(addButton);

        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(80, 23));
        panel_4.add(removeButton);

        chckbxDepositGems = new JCheckBox("Deposit Gems");
        GridBagConstraints gbc_chckbxDepositGems = new GridBagConstraints();
        gbc_chckbxDepositGems.anchor = GridBagConstraints.WEST;
        gbc_chckbxDepositGems.insets = new Insets(0, 0, 0, 5);
        gbc_chckbxDepositGems.gridx = 0;
        gbc_chckbxDepositGems.gridy = 2;
        panel_2.add(chckbxDepositGems, gbc_chckbxDepositGems);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selected Rocks", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        settingsTab.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setAutoscrolls(true);
        panel_3.add(scrollPane, BorderLayout.CENTER);

        mineTypesList = new JList(new DefaultListModel<String>());
        scrollPane.setViewportView(mineTypesList);

        JPanel magicTab = new JPanel();
        magicTab.setBorder(new TitledBorder(null, "Magic Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.addTab("Magic", null, magicTab, null);
        SpringLayout sl_magicTab = new SpringLayout();
        magicTab.setLayout(sl_magicTab);

        chckbxSuperHeat = new JCheckBox("Super Heat");
        sl_magicTab.putConstraint(SpringLayout.NORTH, chckbxSuperHeat, 15, SpringLayout.NORTH, magicTab);
        sl_magicTab.putConstraint(SpringLayout.WEST, chckbxSuperHeat, 30, SpringLayout.WEST, magicTab);
        sl_magicTab.putConstraint(SpringLayout.EAST, chckbxSuperHeat, 179, SpringLayout.WEST, magicTab);
        magicTab.add(chckbxSuperHeat);

        comboBox = new JComboBox();
        sl_magicTab.putConstraint(SpringLayout.NORTH, comboBox, 28, SpringLayout.NORTH, chckbxSuperHeat);
        sl_magicTab.putConstraint(SpringLayout.WEST, comboBox, 30, SpringLayout.WEST, magicTab);
        sl_magicTab.putConstraint(SpringLayout.EAST, comboBox, 179, SpringLayout.WEST, magicTab);
        magicTab.add(comboBox);

        chckbxHighAlch = new JCheckBox("High Alch");
        sl_magicTab.putConstraint(SpringLayout.NORTH, chckbxHighAlch, 28, SpringLayout.NORTH, comboBox);
        sl_magicTab.putConstraint(SpringLayout.WEST, chckbxHighAlch, 30, SpringLayout.WEST, magicTab);
        sl_magicTab.putConstraint(SpringLayout.EAST, chckbxHighAlch, 0, SpringLayout.EAST, comboBox);
        magicTab.add(chckbxHighAlch);

        textField = new JTextField();
        sl_magicTab.putConstraint(SpringLayout.NORTH, textField, 28, SpringLayout.NORTH, chckbxHighAlch);
        sl_magicTab.putConstraint(SpringLayout.WEST, textField, 30, SpringLayout.WEST, magicTab);
        sl_magicTab.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, comboBox);
        magicTab.add(textField);
        textField.setColumns(10);

        JPanel antibanTab = new JPanel();
        antibanTab.setBorder(new TitledBorder(null, "Antiban Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.addTab("Antiban", null, antibanTab, null);
        GridBagLayout gbl_antibanTab = new GridBagLayout();
        gbl_antibanTab.columnWidths = new int[]{0, 0, 0};
        gbl_antibanTab.rowHeights = new int[]{0, 0, 0, 0};
        gbl_antibanTab.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_antibanTab.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        antibanTab.setLayout(gbl_antibanTab);

        JLabel lblRandomCameraMovement = new JLabel("Random Camera Movement:");
        GridBagConstraints gbc_lblRandomCameraMovement = new GridBagConstraints();
        gbc_lblRandomCameraMovement.insets = new Insets(0, 0, 5, 5);
        gbc_lblRandomCameraMovement.anchor = GridBagConstraints.EAST;
        gbc_lblRandomCameraMovement.gridx = 0;
        gbc_lblRandomCameraMovement.gridy = 0;
        antibanTab.add(lblRandomCameraMovement, gbc_lblRandomCameraMovement);

        cameraSlider = new JSlider();
        cameraSlider.setPaintTicks(true);
        cameraSlider.setMajorTickSpacing(10);
        GridBagConstraints gbc_cameraSlider = new GridBagConstraints();
        gbc_cameraSlider.insets = new Insets(0, 0, 5, 0);
        gbc_cameraSlider.gridx = 1;
        gbc_cameraSlider.gridy = 0;
        antibanTab.add(cameraSlider, gbc_cameraSlider);

        JLabel lblRandomMouseMovement = new JLabel("Random Mouse Movement:");
        GridBagConstraints gbc_lblRandomMouseMovement = new GridBagConstraints();
        gbc_lblRandomMouseMovement.anchor = GridBagConstraints.EAST;
        gbc_lblRandomMouseMovement.insets = new Insets(0, 0, 5, 5);
        gbc_lblRandomMouseMovement.gridx = 0;
        gbc_lblRandomMouseMovement.gridy = 1;
        antibanTab.add(lblRandomMouseMovement, gbc_lblRandomMouseMovement);

        mouseSlider = new JSlider();
        mouseSlider.setPaintTicks(true);
        mouseSlider.setMajorTickSpacing(10);
        GridBagConstraints gbc_mouseSlider = new GridBagConstraints();
        gbc_mouseSlider.insets = new Insets(0, 0, 5, 0);
        gbc_mouseSlider.gridx = 1;
        gbc_mouseSlider.gridy = 1;
        antibanTab.add(mouseSlider, gbc_mouseSlider);

        JLabel lblRandomTabChecking = new JLabel("Random Tab Checking:");
        GridBagConstraints gbc_lblRandomTabChecking = new GridBagConstraints();
        gbc_lblRandomTabChecking.insets = new Insets(0, 0, 0, 5);
        gbc_lblRandomTabChecking.anchor = GridBagConstraints.EAST;
        gbc_lblRandomTabChecking.gridx = 0;
        gbc_lblRandomTabChecking.gridy = 2;
        antibanTab.add(lblRandomTabChecking, gbc_lblRandomTabChecking);

        tabCheckingSlider = new JSlider();
        tabCheckingSlider.setMajorTickSpacing(10);
        tabCheckingSlider.setPaintTicks(true);
        GridBagConstraints gbc_tabCheckingSlider = new GridBagConstraints();
        gbc_tabCheckingSlider.gridx = 1;
        gbc_tabCheckingSlider.gridy = 2;
        antibanTab.add(tabCheckingSlider, gbc_tabCheckingSlider);

        JPanel pathCreatorTab = new JPanel();
        pathCreatorTab.setBorder(new TitledBorder(null, "Path Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.addTab("Path Creator", null, pathCreatorTab, null);
        GridBagLayout gbl_pathCreatorTab = new GridBagLayout();
        gbl_pathCreatorTab.columnWidths = new int[]{0, 0, 0};
        gbl_pathCreatorTab.rowHeights = new int[]{0, 0, 0};
        gbl_pathCreatorTab.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_pathCreatorTab.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        pathCreatorTab.setLayout(gbl_pathCreatorTab);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(new TitledBorder(null, "Walk To Bank", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 0;
        pathCreatorTab.add(scrollPane_1, gbc_scrollPane_1);

        listWalkToBank = new JList(new DefaultListModel<PathProfile>());
        listWalkToBank.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_1.setViewportView(listWalkToBank);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Walk From Bank", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
        GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
        gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_2.gridx = 1;
        gbc_scrollPane_2.gridy = 0;
        pathCreatorTab.add(scrollPane_2, gbc_scrollPane_2);

        listWalkFromBank = new JList(new DefaultListModel<PathProfile>());
        listWalkFromBank.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane_2.setViewportView(listWalkFromBank);

        JPanel panel_6 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        GridBagConstraints gbc_panel_6 = new GridBagConstraints();
        gbc_panel_6.anchor = GridBagConstraints.WEST;
        gbc_panel_6.insets = new Insets(0, 0, 0, 5);
        gbc_panel_6.fill = GridBagConstraints.BOTH;
        gbc_panel_6.gridx = 0;
        gbc_panel_6.gridy = 1;
        pathCreatorTab.add(panel_6, gbc_panel_6);

        JLabel lblProfileName = new JLabel("Profile Name:");
        panel_6.add(lblProfileName);

        profileNameTextField = new JTextField();
        panel_6.add(profileNameTextField);
        profileNameTextField.setColumns(10);

        btnCreatePath = new JButton("Create Path");
        GridBagConstraints gbc_btnCreatePath = new GridBagConstraints();
        gbc_btnCreatePath.gridx = 1;
        gbc_btnCreatePath.gridy = 1;
        pathCreatorTab.add(btnCreatePath, gbc_btnCreatePath);

        JPanel feedbackTab = new JPanel();
        feedbackTab.setBorder(new TitledBorder(null, "Please inform us about any suggestions or bug issues.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tabbedPane.addTab("Feedback", null, feedbackTab, null);
        GridBagLayout gbl_feedbackTab = new GridBagLayout();
        gbl_feedbackTab.columnWidths = new int[]{0, 0};
        gbl_feedbackTab.rowHeights = new int[]{0, 0, 0};
        gbl_feedbackTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_feedbackTab.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        feedbackTab.setLayout(gbl_feedbackTab);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
        gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
        gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_3.gridx = 0;
        gbc_scrollPane_3.gridy = 0;
        feedbackTab.add(scrollPane_3, gbc_scrollPane_3);

        feedbackTextArea = new JTextArea();
        feedbackTextArea.setLineWrap(true);
        scrollPane_3.setViewportView(feedbackTextArea);

        btnSubmit = new JButton("Submit");
        GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
        gbc_btnSubmit.gridx = 0;
        gbc_btnSubmit.gridy = 1;
        feedbackTab.add(btnSubmit, gbc_btnSubmit);

        // TODO: Delete following lines upon completion
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public JButton getBtnStop() {
        return btnStop;
    }

    public JComboBox getBankTypeComboBox() {
        return bankTypeComboBox;
    }

    public JComboBox getMineTypeComboBox() {
        return mineTypeComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JCheckBox getChckbxSuperHeat() {
        return chckbxSuperHeat;
    }

    public JCheckBox getChckbxDepositGems() {
        return chckbxDepositGems;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JCheckBox getChckbxHighAlch() {
        return chckbxHighAlch;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JList getMineTypesList() { return mineTypesList; }

    public JList getListWalkToBank() {
        return listWalkToBank;
    }

    public JList getListWalkFromBank() {
        return listWalkFromBank;
    }

    public JButton getBtnCreatePath() {
        return btnCreatePath;
    }

    public JTextField getProfileNameTextField() {
        return profileNameTextField;
    }

    public JSpinner getWorkingRadiusSpinner() {
        return workingRadiusSpinner;
    }

    public JCheckBox getChckbxUsePathCreator() {
        return chckbxUsePathCreator;
    }

    public JSlider getCheckingSlider() {
        return tabCheckingSlider;
    }

    public JSlider getCameraSlider() {
        return cameraSlider;
    }

    public JSlider getMouseSlider() {
        return mouseSlider;
    }

    public JTextArea getFeedbackTextArea() {
        return feedbackTextArea;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }
}
