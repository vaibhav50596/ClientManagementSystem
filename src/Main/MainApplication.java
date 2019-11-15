package Main;

import Controller.ClientManagementController;
import Model.ClientManagementModel;
import View.ClientManagementView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    public Connection jdbc_connection;
    public PreparedStatement preparedStatement;
    public String databaseName = "ClientManagementDB",
            tableName = "ClientTable",
            dataFile = "C:\\Users\\vaibh\\Desktop\\MEng. Soft. Engg\\ENSF-593 and ENSF-594\\Programs\\Lab 8 - Client Management System\\src\\Model\\clients.txt";

    public String connectionInfo = "jdbc:mysql://localhost:3306/ClientManagementDB?verifyServerCertificate=false&useSSL=true",
            login          = "root",
            password       = "Vahbiav#469882";
    private ClientManagementView clientManagementView = new ClientManagementView();
    private ArrayList<ClientManagementModel> modelJListData = new ArrayList<ClientManagementModel>();

    public MainApplication() {
        try{
            // If this throws an error, make sure you have added the mySQL connector JAR to the project
            Class.forName("com.mysql.jdbc.Driver");

            // If this fails make sure your connectionInfo and login/password are correct
            jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);

            System.out.println("Connected to: " + connectionInfo + "\n");
        }
        catch(SQLException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void createDB() {
        try {
            preparedStatement = jdbc_connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + databaseName);
            preparedStatement.executeUpdate();
        }
        catch( SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                       "ID INT(4) NOT NULL AUTO_INCREMENT, " +
                       "FirstName VARCHAR(20) NOT NULL, " +
                       "LastName VARCHAR(20) NOT NULL, " +
                       "Address VARCHAR(50) NOT NULL, " +
                       "PostalCode CHAR(7) NOT NULL, " +
                       "PhoneNumber CHAR(12) NOT NULL, " +
                       "ClientType CHAR(1) NOT NULL, " +
                       "PRIMARY KEY (ID))";
        try {
            preparedStatement = jdbc_connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Table is created");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDataInTable() {
        try{
            Scanner sc = new Scanner(new FileReader(dataFile));
            while(sc.hasNext()) {
                String clienManagementModel[] = sc.nextLine().split(";");
                ClientManagementModel model = new ClientManagementModel();
                model.setFirstName(clienManagementModel[0]);
                model.setLastName(clienManagementModel[1]);
                model.setAddress(clienManagementModel[2]);
                model.setPostalCode(clienManagementModel[3]);
                model.setPhoneNumber(clienManagementModel[4]);
                model.setClientType(clienManagementModel[5]);
                addClient(model);
            }
            sc.close();
        }
        catch(FileNotFoundException e) { System.err.println("File " + dataFile + " Not Found!"); }
        catch(Exception e) { e.printStackTrace(); }
    }

    public void addClient(ClientManagementModel model) {
        String sql = "INSERT INTO " + tableName + "(FirstName, LastName, Address, PostalCode, PhoneNumber, ClientType)" +
                " VALUES (?,?,?,?,?,?) ";
        try{
            preparedStatement = jdbc_connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setString(3, model.getAddress());
            preparedStatement.setString(4, model.getPostalCode());
            preparedStatement.setString(5, model.getPhoneNumber());
            preparedStatement.setString(6, model.getClientType());
            preparedStatement.executeUpdate();
            System.out.println("New client is added to the database");
        } catch(SQLException e) { e.printStackTrace(); }
    }

    public void updateClient(ClientManagementModel model) {
        String sql = "UPDATE " + tableName + " SET FirstName = ? , LastName = ? , Address = ? , " +
                " PostalCode = ? , PhoneNumber = ? , ClientType = ? WHERE ID = ?";
        try{
            preparedStatement = jdbc_connection.prepareStatement(sql);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setString(3, model.getAddress());
            preparedStatement.setString(4, model.getPostalCode());
            preparedStatement.setString(5, model.getPhoneNumber());
            preparedStatement.setString(6, model.getClientType());
            preparedStatement.setInt(7, model.getId());
            preparedStatement.executeUpdate();
            System.out.println("Client is updated to the database");
        } catch(SQLException e) { e.printStackTrace(); }
    }

    public void getCliendRow(String columnName, String value, ArrayList<ClientManagementModel> list) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " =  ? ";
        try {
            preparedStatement = jdbc_connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                ClientManagementModel model = new ClientManagementModel();
                model.setId(rs.getInt("ID"));
                model.setFirstName(rs.getString("FirstName"));
                model.setLastName(rs.getString("LastName"));
                model.setAddress(rs.getString("Address"));
                model.setPostalCode(rs.getString("PostalCode"));
                model.setPhoneNumber(rs.getString("PhoneNumber"));
                model.setClientType(rs.getString("ClientType"));
                list.add(model);
            }
            this.modelJListData.clear();
            this.modelJListData = list;
            clientManagementView.setClientData(this.modelJListData);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClientById(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ? ";
        try {
            preparedStatement = jdbc_connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Client is deleted");
            updateModelList(id);
            clientManagementView.clearRightSideData();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateModelList(int id) {
        for(int i = 0; i < this.modelJListData.size(); i++) {
            if(this.modelJListData.get(i).getId() == id) {
                this.modelJListData.remove(i);
                clientManagementView.setJList(this.modelJListData);
            }
        }
    }

    public void addOrUpdateClient() {
        ClientManagementModel model = new ClientManagementModel();
        model = clientManagementView.getClientData(model);
        if(model.getId() != 0) {
            updateClient(model);
        } else {
            addClient(model);
        }
        clientManagementView.clearRightSideData();
    }

    public void deleteClient() {
        int id = clientManagementView.getClientToBeDeleted();
        deleteClientById(id);
    }

    public static void main(String [] args) {
        MainApplication application = new MainApplication();
        ClientManagementController clientManagementController = new ClientManagementController(application.clientManagementView, application);

        //application.createDB();
        //application.createTable();
        //application.loadDataInTable();
    }
}
