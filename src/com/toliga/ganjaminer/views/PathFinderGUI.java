package com.toliga.ganjaminer.views;

import com.toliga.ganjabots.core.SaveManager;
import com.toliga.ganjabots.path.ActionElement;
import com.toliga.ganjabots.path.PathElement;
import com.toliga.ganjabots.path.PathProfile;
import org.dreambot.api.script.AbstractScript;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PathFinderGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private static int visibleIndex = 1;
    private JPanel contentPane;
    private JTextField idTextField;
    private JTextField actionTextField;
    private JButton btnAddAction;
    private JButton btnSaveCurrentTile;
    private DefaultListModel<String> toListModel = new DefaultListModel<>();
    private DefaultListModel<String> fromListModel = new DefaultListModel<>();
    private AbstractScript context;
    private PathProfile toPathProfile;
    private PathProfile fromPathProfile;
    private JButton btnSaveProfile;
    private JRadioButton rdbtnToBank;
    private JRadioButton rdbtnFromBank;
    private SaveManager saveManager;

    /**
     * Create the frame.
     */
    public PathFinderGUI(AbstractScript context, PathProfile toPathProfile, PathProfile fromPathProfile, SaveManager saveManager) {
        this.context = context;
        this.toPathProfile = toPathProfile;
        this.fromPathProfile = fromPathProfile;
        this.saveManager = saveManager;
        setTitle("GanjaSmuggler - Path Creator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 470, 300);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.NORTH);
        panel_1.setLayout(new GridLayout(2, 2, 0, 0));

        JLabel lblNewLabel = new JLabel("Path Creator");
        panel_1.add(lblNewLabel);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        panel_1.add(panel);

        JLabel lblId = new JLabel("ID:");
        panel.add(lblId);

        idTextField = new JTextField();
        panel.add(idTextField);
        idTextField.setColumns(5);

        JLabel lblAction = new JLabel("Action:");
        panel.add(lblAction);

        actionTextField = new JTextField();
        panel.add(actionTextField);
        actionTextField.setColumns(7);

        btnAddAction = new JButton("Add ");
        panel.add(btnAddAction);

        btnSaveCurrentTile = new JButton("Save Current Tile");
        panel.add(btnSaveCurrentTile);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.SOUTH);

        btnSaveProfile = new JButton("Save Profile");
        panel_2.add(btnSaveProfile);

        JPanel panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new GridLayout(1, 2, 5, 5));

        JPanel panel_4 = new JPanel();
        panel_3.add(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        rdbtnToBank = new JRadioButton("To Bank");
        rdbtnToBank.setHorizontalAlignment(SwingConstants.CENTER);
        panel_4.add(rdbtnToBank, BorderLayout.NORTH);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setAutoscrolls(true);

        JList toBankList = new JList(toListModel);
        scrollPane_1.setViewportView(toBankList);
        panel_4.add(scrollPane_1, BorderLayout.CENTER);

        JPanel panel_5 = new JPanel();
        panel_3.add(panel_5);
        panel_5.setLayout(new BorderLayout(0, 0));

        rdbtnFromBank = new JRadioButton("From Bank");
        rdbtnFromBank.setHorizontalAlignment(SwingConstants.CENTER);
        panel_5.add(rdbtnFromBank, BorderLayout.NORTH);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setAutoscrolls(true);

        JList fromBankList = new JList(fromListModel);
        scrollPane_2.setViewportView(fromBankList);
        panel_5.add(scrollPane_2, BorderLayout.CENTER);

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdbtnFromBank);
        btnGroup.add(rdbtnToBank);
        registerEvents();
    }

    private void registerEvents() {
        btnSaveCurrentTile.addActionListener(event -> {
            PathElement pathElement = new PathElement(context.getLocalPlayer().getTile().getX(), context.getLocalPlayer().getTile().getY());

            if (rdbtnToBank.isSelected()) {
                toPathProfile.addElement(pathElement);
                toListModel.addElement(visibleIndex++ + ". " + pathElement.toString());
            } else if (rdbtnFromBank.isSelected()) {
                fromPathProfile.addElement(pathElement);
                fromListModel.addElement(visibleIndex++ + ". " + pathElement.toString());
            }
        });

        btnAddAction.addActionListener(event -> {
            ActionElement actionElement = new ActionElement(Integer.parseInt(idTextField.getText()), actionTextField.getText());

            if (rdbtnToBank.isSelected()) {
                toPathProfile.addElement(actionElement);
                toListModel.addElement(visibleIndex++ + ". " + actionElement.toString());
            } else if (rdbtnFromBank.isSelected()) {
                fromPathProfile.addElement(actionElement);
                fromListModel.addElement(visibleIndex++ + ". " + actionElement.toString());
            }
        });

        btnSaveProfile.addActionListener(event -> {
            toPathProfile.saveProfile(saveManager);
            fromPathProfile.saveProfile(saveManager);
            setVisible(false);
            dispose();
        });
    }
}
