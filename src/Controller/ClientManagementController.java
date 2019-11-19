package Controller;

import Model.DBModel;
import Model.ClientManagementModel;
import View.ClientManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientManagementController.
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since November 18, 2019
 */
public class ClientManagementController {
    
    /** The client management view. */
    private ClientManagementView clientManagementView;
    
    /** The main application. */
    private DBModel mainApplication;

    /**
     * Instantiates a new client management controller.
     *
     * @param cmv the cmv
     * @param myapp the myapp
     */
    public ClientManagementController(ClientManagementView cmv, DBModel myapp) {
        this.clientManagementView = cmv;
        this.mainApplication = myapp;
        attachListeners();
    }

    /**
     * Attach listeners.
     */
    public void attachListeners() {
        attachSearchListener();
        attachClearSearchListener();
        attachNewClientListener();
        saveClientListener();
        clearDataListener();
        deleteClientListener();
        attachValidations();
    }

    /**
     * Attach search listener.
     */
    public void attachSearchListener() {
        this.clientManagementView.getSearchButton().addActionListener((ActionEvent e) -> {
            ArrayList<ClientManagementModel> list = new ArrayList<ClientManagementModel>();
            boolean isCheck = false;
            if(clientManagementView.getClientIdRadio().isSelected()) {
                try {
                    int id = Integer.parseInt(clientManagementView.getInputSearchText().getText());
                    list = mainApplication.getCliendRow("ID", String.valueOf(id), list);
                } catch (NumberFormatException err) {
                    clientManagementView.showMessage("Please enter valid ID");
                    isCheck = true;
                }
            } else if(clientManagementView.getlNameRadio().isSelected()) {
                list = mainApplication.getCliendRow("LastName", clientManagementView.getInputSearchText().getText(), list);
            } else if(clientManagementView.getClientTypeRadio().isSelected()) {
                list = mainApplication.getCliendRow("ClientType", clientManagementView.getInputSearchText().getText(), list);
            }
            if(list.size() == 0 && !isCheck) {
                clientManagementView.showMessage("Please enter valid search criteria");
            }
            clientManagementView.setClientData(list);
        });
    }

    /**
     * Attach clear search listener.
     */
    public void attachClearSearchListener() {
        this.clientManagementView.getClearSearchButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearSearchCriteria();
        });
    }

    /**
     * Attach new client listener.
     */
    public void attachNewClientListener() {
        this.clientManagementView.getAddNewButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearRightSideData();
        });
    }

    /**
     * Save client listener.
     */
    public void saveClientListener() {
        this.clientManagementView.getSaveButton().addActionListener((ActionEvent e) -> {
            ClientManagementModel model = new ClientManagementModel();
            model = clientManagementView.getClientData(model);
            if(model.getFirstName().trim().isEmpty() || model.getLastName().trim().isEmpty() || model.getPostalCode().trim().isEmpty()
            ||model.getClientType().trim().isEmpty() || model.getAddress().trim().isEmpty() || model.getPhoneNumber().trim().equals("-   -")) {
                clientManagementView.showMessage("Please enter all fields");
            } else if (model.getFirstName().length() > 20) {
                clientManagementView.showMessage("First name field should be less than 20 characters");
            } else if (model.getLastName().length() > 20) {
                clientManagementView.showMessage("Last name field should be less than 20 characters");
            } else if (model.getAddress().length() > 50) {
                clientManagementView.showMessage("Address field should be less than 50 characters");
            } else {
                if(model.getId() != 0) {
                    mainApplication.updateClient(model);
                } else {
                    mainApplication.addClient(model);
                }
                clientManagementView.clearRightSideData();
            }
            //mainApplication.addOrUpdateClient();
        });
    }

    /**
     * Clear data listener.
     */
    public void clearDataListener() {
        this.clientManagementView.getClearInfoButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearRightSideData();
        });
    }

    /**
     * Delete client listener.
     */
    public void deleteClientListener() {
        this.clientManagementView.getDeleteButton().addActionListener((ActionEvent e) -> {
            int id = clientManagementView.getClientToBeDeleted();
            ArrayList<ClientManagementModel> list = mainApplication.deleteClient(id);
            if(list != null && list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getId() == id) {
                        list.remove(i);
                        clientManagementView.setJList(list);
                    }
                }
                clientManagementView.clearRightSideData();
            }
        });
    }

    /**
     * Attach validations.
     */
    public void attachValidations() {
        this.clientManagementView.getfNameText().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (clientManagementView.getfNameText().getText().length() >= 20 ){
                    e.consume();
                }
            }
        });
        this.clientManagementView.getlNameText().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (clientManagementView.getlNameText().getText().length() >= 20 ){
                    e.consume();
                }
            }
        });
        this.clientManagementView.getAddressText().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (clientManagementView.getAddressText().getText().length() >= 50 ){
                    e.consume();
                }
            }
        });
    }
}
