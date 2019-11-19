import Controller.ClientManagementController;
import Model.DBModel;
import View.ClientManagementView;

// TODO: Auto-generated Javadoc
/**
 * The Class FrontendApp.
 * @author Vaibhav V. Jadhav
 * @version 1.0
 * @since November 18, 2019
 */
public class FrontendApp {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String [] args) {
        DBModel application = new DBModel();
        ClientManagementView clientManagementView = new ClientManagementView();
        ClientManagementController clientManagementController = new ClientManagementController(clientManagementView, application);
    }
}
