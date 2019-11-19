package Model;

import Controller.ClientManagementController;
import View.ClientManagementView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class DBModel.
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since November 18, 2019
 */
public class DBModel {
    
    /** The jdbc connection. */
    public Connection jdbc_connection;
    
    /** The prepared statement. */
    public PreparedStatement preparedStatement;
    
    /** The data file. */
    public String databaseName = "ClientManagementDB",
            tableName = "ClientTable",
            dataFile = "C:\\Users\\vaibh\\Desktop\\MEng. Soft. Engg\\ENSF-593 and ENSF-594\\Programs\\Lab 8 - Client Management System\\src\\Model\\clients.txt";

    /** The password. */
    public String connectionInfo = "jdbc:mysql://localhost:3306/?verifyServerCertificate=false&useSSL=true",
            login          = "root",
            password       = "Vahbiav#469882";
    
    /** The model J list data. */
    //private ClientManagementView clientManagementView = new ClientManagementView();
    private ArrayList<ClientManagementModel> modelJListData = new ArrayList<ClientManagementModel>();

    /**
     * Instantiates a new DB model.
     */
    public DBModel() {
        try{
            // If this throws an error, make sure you have added the mySQL connector JAR to the project
            // Class.forName("com.mysql.jdbc.Driver");

            // If this fails make sure your connectionInfo and login/password are correct
            jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
            System.out.println("Connected to: " + connectionInfo + "\n");
            String show = "show databases";
            String use = "use " + databaseName;
            try {
                preparedStatement = jdbc_connection.prepareStatement(show);
                preparedStatement.executeQuery();
                preparedStatement = jdbc_connection.prepareStatement(use);
                preparedStatement.executeQuery();
                connectionInfo = "jdbc:mysql://localhost:3306/ClientManagementDB?verifyServerCertificate=false&useSSL=true";
                jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
                System.out.println("Connected to: " + connectionInfo + "\n");
                System.out.println("DB already exist");
            } catch (SQLException e) {
                System.out.println("You should create db");
                createDB();
                connectionInfo = "jdbc:mysql://localhost:3306/ClientManagementDB?verifyServerCertificate=false&useSSL=true";
                jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
                createTable();
                loadDataInTable();
            }
        }
        catch(SQLException e) { e.printStackTrace(); }
        catch(Exception e) { e.printStackTrace(); }
    }

    /**
     * Creates the DB.
     */
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

    /**
     * Creates the table.
     */
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

    /**
     * Load data in table.
     */
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

    /**
     * Adds the client.
     *
     * @param model the model
     */
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

    /**
     * Update client.
     *
     * @param model the model
     */
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

    /**
     * Gets the cliend row.
     *
     * @param columnName the column name
     * @param value the value
     * @param list the list
     * @return the cliend row
     */
    public ArrayList<ClientManagementModel> getCliendRow(String columnName, String value, ArrayList<ClientManagementModel> list) {
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
            //clientManagementView.setClientData(this.modelJListData);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return this.modelJListData;
    }

    /**
     * Delete client by id.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean deleteClientById(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ? ";
        try {
            preparedStatement = jdbc_connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int value = preparedStatement.executeUpdate();
            if(value > 0) {
                System.out.println("Client is deleted");
                return true;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public void updateModelList(int id) {
//        for(int i = 0; i < this.modelJListData.size(); i++) {
//            if(this.modelJListData.get(i).getId() == id) {
//                this.modelJListData.remove(i);
//                clientManagementView.setJList(this.modelJListData);
//            }
//        }
//    }

//    public void addOrUpdateClient() {
//        ClientManagementModel model = new ClientManagementModel();
//        //model = clientManagementView.getClientData(model);
//        if(model.getId() != 0) {
//            updateClient(model);
//        } else {
//            addClient(model);
//        }
//        clientManagementView.clearRightSideData();
//    }

    /**
 * Delete client.
 *
 * @param id the id
 * @return the array list
 */
public ArrayList<ClientManagementModel> deleteClient(int id) {
        boolean value = deleteClientById(id);
        if(value) {
            return this.modelJListData;
        }
        return null;
    }
}
