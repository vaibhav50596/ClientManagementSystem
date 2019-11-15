package Controller;

import Main.MainApplication;
import Model.ClientManagementModel;
import View.ClientManagementView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ClientManagementController {
    private ClientManagementView clientManagementView;
    private MainApplication mainApplication;

    public ClientManagementController(ClientManagementView cmv, MainApplication myapp) {
        this.clientManagementView = cmv;
        this.mainApplication = myapp;
        attachListeners();
    }

    public void attachListeners() {
        attachSearchListener();
        attachClearSearchListener();
        attachNewClientListener();
        saveClientListener();
        clearDataListener();
        deleteClientListener();
    }

    public void attachSearchListener() {
        this.clientManagementView.getSearchButton().addActionListener((ActionEvent e) -> {
            ArrayList<ClientManagementModel> list = new ArrayList<ClientManagementModel>();
            if(clientManagementView.getClientIdRadio().isSelected()) {
                mainApplication.getCliendRow("ID", clientManagementView.getInputSearchText().getText(), list);
            } else if(clientManagementView.getlNameRadio().isSelected()) {
                mainApplication.getCliendRow("LastName", clientManagementView.getInputSearchText().getText(), list);
            } else if(clientManagementView.getClientTypeRadio().isSelected()) {
                mainApplication.getCliendRow("ClientType", clientManagementView.getInputSearchText().getText(), list);
            }
        });
    }

    public void attachClearSearchListener() {
        this.clientManagementView.getClearSearchButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearSearchCriteria();
        });
    }

    public void attachNewClientListener() {
        this.clientManagementView.getAddNewButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearRightSideData();
        });
    }

    public void saveClientListener() {
        this.clientManagementView.getSaveButton().addActionListener((ActionEvent e) -> {
            mainApplication.addOrUpdateClient();
        });
    }

    public void clearDataListener() {
        this.clientManagementView.getClearInfoButton().addActionListener((ActionEvent e) -> {
            this.clientManagementView.clearRightSideData();
        });
    }

    public void deleteClientListener() {
        this.clientManagementView.getDeleteButton().addActionListener((ActionEvent e) -> {
            mainApplication.deleteClient();
        });
    }
}
