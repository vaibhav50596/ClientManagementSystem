package View;

import Controller.ClientManagementController;
import Model.ClientManagementModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientManagementView.
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since November 18, 2019
 */
public class ClientManagementView extends JFrame {
    
    /** The search results. */
    private JLabel appLabel, leftPanelLabel, rightPanelLabel, searchTypeLabel,
    searchOptionLabel, searchResults;
    
    /** The search button. */
    private JButton searchButton;
    
    /** The clear search button. */
    private JButton clearSearchButton;
    
    /** The save button. */
    private JButton saveButton;
    
    /** The delete button. */
    private JButton deleteButton;
    
    /** The clear info button. */
    private JButton clearInfoButton;
    
    /** The add new button. */
    private JButton addNewButton;
    
    /** The client type radio. */
    private JRadioButton clientIdRadio, lNameRadio, clientTypeRadio;
    
    /** The input search text. */
    private JTextField inputSearchText;
    
    /** The id text. */
    private JTextField idText;
    
    /** The name text. */
    private JTextField fNameText;
    
    /** The l name text. */
    private JTextField lNameText;
    
    /** The address text. */
    private JTextField addressText;
    
    /** The zip text. */
    private JFormattedTextField phoneText, zipText;
    
    /** The client type combo box. */
    private JComboBox clientTypeComboBox;
    
    /** The search result list. */
    private JList searchResultList;
    
    /** The button group. */
    private ButtonGroup buttonGroup;

    /**
     * Instantiates a new client management view.
     */
    public ClientManagementView() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        createChildPanels(mainPanel, constraints);
        setSize(800, 550);
        add(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the child panels.
     *
     * @param mainPanel the main panel
     * @param constraints the constraints
     */
    public void createChildPanels(JPanel mainPanel, GridBagConstraints constraints) {
        appLabel = new JLabel("Client Management System");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        mainPanel.add(appLabel);

        constraints.gridx = 0;
        constraints.gridy = 1;
        populateLeftSide(mainPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        populateBelowLeftSide(mainPanel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridheight = 2;
        populateRightSide(mainPanel, constraints);
    }

    /**
     * Populate left side.
     *
     * @param mainPanel the main panel
     * @param constraints the constraints
     */
    public void populateLeftSide(JPanel mainPanel, GridBagConstraints constraints) {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        leftPanelLabel = new JLabel("Search Clients");
        GridBagConstraints childConstraints = new GridBagConstraints();
        childConstraints.gridx = 0;
        childConstraints.gridy = 0;
        leftPanel.add(leftPanelLabel, childConstraints);
        searchTypeLabel = new JLabel("Select type of search to be performed");
        childConstraints.gridx = 0;
        childConstraints.gridy = 1;
        leftPanel.add(searchTypeLabel, childConstraints);
        clientIdRadio = new JRadioButton("Client ID");
        lNameRadio = new JRadioButton("Last Name");
        clientTypeRadio = new JRadioButton("Client Type");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(clientIdRadio);
        buttonGroup.add(lNameRadio);
        buttonGroup.add(clientTypeRadio);
        childConstraints.gridx = 0;
        childConstraints.gridy = 2;
        leftPanel.add(clientIdRadio, childConstraints);
        childConstraints.gridx = 0;
        childConstraints.gridy = 3;
        leftPanel.add(lNameRadio, childConstraints);
        childConstraints.gridx = 0;
        childConstraints.gridy = 4;
        leftPanel.add(clientTypeRadio, childConstraints);
        searchOptionLabel = new JLabel("Enter the search parameter below");
        childConstraints.gridx = 0;
        childConstraints.gridy = 5;
        leftPanel.add(searchOptionLabel, childConstraints);
        inputSearchText = new JTextField();
        inputSearchText.setPreferredSize(new Dimension(120, 20));
        childConstraints.gridx = 0;
        childConstraints.gridy = 6;
        leftPanel.add(inputSearchText, childConstraints);
        searchButton = new JButton("Search");
        childConstraints.gridx = 1;
        childConstraints.gridy = 6;
        leftPanel.add(searchButton, childConstraints);
        clearSearchButton = new JButton("Clear Search");
        childConstraints.gridx = 2;
        childConstraints.gridy = 6;
        leftPanel.add(clearSearchButton, childConstraints);
        mainPanel.add(leftPanel, constraints);
    }

    /**
     * Populate below left side.
     *
     * @param mainPanel the main panel
     * @param constraints the constraints
     */
    public void populateBelowLeftSide(JPanel mainPanel, GridBagConstraints constraints) {
        JPanel leftBelowPanel = new JPanel(new GridBagLayout());
        leftBelowPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        GridBagConstraints childConstraints = new GridBagConstraints();
        searchResults = new JLabel("Search Results: ");
        childConstraints.gridx = 0;
        childConstraints.gridy = 0;
        leftBelowPanel.add(searchResults, childConstraints);

        searchResultList = new JList();
        searchResultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchResultList.setLayoutOrientation(JList.VERTICAL);
        JScrollPane searchResultScroll = new JScrollPane(searchResultList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        searchResultScroll.setPreferredSize(new Dimension(400, 200));
        childConstraints.gridx = 0;
        childConstraints.gridy = 1;
        leftBelowPanel.add(searchResultScroll, childConstraints);
        mainPanel.add(leftBelowPanel, constraints);
    }

    /**
     * Populate right side.
     *
     * @param mainPanel the main panel
     * @param constraints the constraints
     */
    public void populateRightSide(JPanel mainPanel, GridBagConstraints constraints) {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        GridBagConstraints childConstraints = new GridBagConstraints();
        rightPanelLabel = new JLabel("Client Information");
        childConstraints.gridx = 0;
        childConstraints.gridy = 0;
        rightPanel.add(rightPanelLabel);
        idText = new JTextField();
        idText.setDisabledTextColor(Color.gray);
        idText.setEditable(false);
        idText.setPreferredSize(new Dimension(120, 20));
        setLabelAndText("Client ID: ", idText,0, 1, 1, 1, childConstraints, rightPanel);
        fNameText = new JTextField();
        fNameText.setPreferredSize(new Dimension(120, 20));
        //fNameText.setColumns(20);
        setLabelAndText("First Name: ", fNameText,0, 2, 1, 2, childConstraints, rightPanel);
        lNameText = new JTextField();
        lNameText.setPreferredSize(new Dimension(120, 20));
        //lNameText.setColumns(20);
        setLabelAndText("Last Name: ", lNameText,0, 3, 1, 3, childConstraints, rightPanel);
        addressText = new JTextField();
        addressText.setPreferredSize(new Dimension(120, 20));
        //addressText.setColumns(50);
        setLabelAndText("Address: ", addressText,0, 4, 1, 4, childConstraints, rightPanel);
        try {
            zipText = new JFormattedTextField(new MaskFormatter("?#? #?#"));
            zipText.setColumns(7);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        zipText.setPreferredSize(new Dimension(120, 20));
        setLabelAndText("Postal Code: ", zipText,0, 5, 1, 5, childConstraints, rightPanel);
        try {
            phoneText = new JFormattedTextField(new MaskFormatter("###-###-####"));
            phoneText.setColumns(12);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneText.setPreferredSize(new Dimension(120, 20));
        setLabelAndText("Phone Number: ", phoneText,0, 6, 1, 6, childConstraints, rightPanel);
        JLabel clientTypeLabel = new JLabel("Client Type: ");
        childConstraints.gridx = 0;
        childConstraints.gridy = 7;
        rightPanel.add(clientTypeLabel, childConstraints);
        clientTypeComboBox = new JComboBox(new String[]{" ", "R", "C"});
        childConstraints.gridx = 1;
        childConstraints.gridy = 7;
        rightPanel.add(clientTypeComboBox, childConstraints);
        saveButton = new JButton("Save");
        childConstraints.gridx = 0;
        childConstraints.gridy = 8;
        rightPanel.add(saveButton, childConstraints);
        deleteButton = new JButton("Delete");
        childConstraints.gridx = 1;
        childConstraints.gridy = 8;
        rightPanel.add(deleteButton, childConstraints);
        clearInfoButton = new JButton("Clear");
        childConstraints.gridx = 2;
        childConstraints.gridy = 8;
        rightPanel.add(clearInfoButton, childConstraints);
        addNewButton = new JButton("Add New Client");
        childConstraints.gridx = 1;
        childConstraints.gridy = 9;
        rightPanel.add(addNewButton, childConstraints);
        mainPanel.add(rightPanel, constraints);
    }

    /**
     * Sets the label and text.
     *
     * @param labelName the label name
     * @param textField the text field
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @param childConstraints the child constraints
     * @param rightPanel the right panel
     */
    public void setLabelAndText(String labelName, JTextField textField, int x1, int y1, int x2, int y2, GridBagConstraints childConstraints, JPanel rightPanel) {
        JLabel idLabel = new JLabel(labelName);
        childConstraints.gridx = x1;
        childConstraints.gridy = y1;
        rightPanel.add(idLabel, childConstraints);
        childConstraints.gridx = x2;
        childConstraints.gridy = y2;
        rightPanel.add(textField, childConstraints);
    }

    /**
     * Sets the client data.
     *
     * @param list the new client data
     */
    public void setClientData(ArrayList<ClientManagementModel> list) {
        searchResultList.setListData(list.toArray());
        searchResultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting() && searchResultList.getSelectedIndex() > -1) {
                    fillRightSideData(list, searchResultList.getSelectedIndex());
                }
            }
        });
    }

    /**
     * Fill right side data.
     *
     * @param model the model
     * @param index the index
     */
    public void fillRightSideData(ArrayList<ClientManagementModel> model, int index) {
        for(int i = 0; i < model.size(); i++) {
            if(index == i) {
                //clearRightSideData();
                idText.setText(String.valueOf(model.get(i).getId()));
                fNameText.setText(model.get(i).getFirstName());
                lNameText.setText(model.get(i).getLastName());
                addressText.setText(model.get(i).getAddress());
                zipText.setText(model.get(i).getPostalCode());
                phoneText.setText(model.get(i).getPhoneNumber());
                clientTypeComboBox.setSelectedItem(model.get(i).getClientType());
            }
        }
    }

    /**
     * Clear search criteria.
     */
    public void clearSearchCriteria() {
        inputSearchText.setText("");
        buttonGroup.clearSelection();
        searchResultList.setListData(new ArrayList<ClientManagementModel>().toArray());
    }

    /**
     * Gets the client data.
     *
     * @param model the model
     * @return the client data
     */
    public ClientManagementModel getClientData(ClientManagementModel model) {
        if(idText.getText() != null && !idText.getText().equals("")) {
            model.setId(Integer.parseInt(idText.getText()));
        }
        model.setFirstName(fNameText.getText());
        model.setLastName(lNameText.getText());
        model.setAddress(addressText.getText());
        model.setPostalCode(zipText.getText());
        model.setPhoneNumber(phoneText.getText());
        model.setClientType(String.valueOf(clientTypeComboBox.getSelectedItem()));
        return model;
    }

    /**
     * Clear right side data.
     */
    public void clearRightSideData() {
        idText.setText("");
        fNameText.setText("");
        lNameText.setText("");
        addressText.setText("");
        zipText.setText("");
        phoneText.setText("");
        clientTypeComboBox.setSelectedIndex(0);
    }

    /**
     * Show message.
     *
     * @param message the message
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Gets the client to be deleted.
     *
     * @return the client to be deleted
     */
    public int getClientToBeDeleted() {
        try {
            return Integer.parseInt(idText.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
        return 0;
    }

    /**
     * Sets the j list.
     *
     * @param list the new j list
     */
    public void setJList(ArrayList<ClientManagementModel> list) {
        searchResultList.setListData(list.toArray());
    }

    /**
     * Gets the search button.
     *
     * @return the search button
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * Gets the clear search button.
     *
     * @return the clear search button
     */
    public JButton getClearSearchButton() {
        return clearSearchButton;
    }

    /**
     * Gets the client id radio.
     *
     * @return the client id radio
     */
    public JRadioButton getClientIdRadio() {
        return clientIdRadio;
    }

    /**
     * Gets the l name radio.
     *
     * @return the l name radio
     */
    public JRadioButton getlNameRadio() {
        return lNameRadio;
    }

    /**
     * Gets the client type radio.
     *
     * @return the client type radio
     */
    public JRadioButton getClientTypeRadio() {
        return clientTypeRadio;
    }

    /**
     * Gets the input search text.
     *
     * @return the input search text
     */
    public JTextField getInputSearchText() {
        return inputSearchText;
    }

    /**
     * Gets the adds the new button.
     *
     * @return the adds the new button
     */
    public JButton getAddNewButton() {
        return addNewButton;
    }

    /**
     * Gets the save button.
     *
     * @return the save button
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * Gets the delete button.
     *
     * @return the delete button
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }

    /**
     * Gets the clear info button.
     *
     * @return the clear info button
     */
    public JButton getClearInfoButton() {
        return clearInfoButton;
    }

    /**
     * Gets the f name text.
     *
     * @return the f name text
     */
    public JTextField getfNameText() { return fNameText; }

    /**
     * Gets the l name text.
     *
     * @return the l name text
     */
    public JTextField getlNameText() { return lNameText; }

    /**
     * Gets the address text.
     *
     * @return the address text
     */
    public JTextField getAddressText() { return addressText; }
}
