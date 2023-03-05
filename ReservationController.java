/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import entities.Reservation;
import entities.historique_reservation;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static javafx.scene.control.AccordionBuilder.create;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.historique_reservationCRUD;
import services.reservationCRUD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class ReservationController implements Initializable {

    @FXML
    private Button btnConfirmerReservation;
    @FXML
    private TextField tfIdOffre;
    @FXML
    private TextField tfIdClient;
    @FXML
    private TextField tfPointDepart;
    @FXML
    private TextField tfPointArrive;
    @FXML
    private TextField tfIdConcucteur;
    @FXML
    private TextField tfNombrePlaces;
    @FXML
    private Button btnHistorique;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfIdRes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void confirmer(ActionEvent event) {
        int resNbPlace = Integer.parseInt(tfNombrePlaces.getText());
        int resId_offre = Integer.parseInt(tfIdOffre.getText());
        int resIdClient = Integer.parseInt(tfIdClient.getText());
        String resPointDepart = tfPointDepart.getText();
        String resPointArrive = tfPointArrive.getText();
        int resIdConducteur = Integer.parseInt(tfIdConcucteur.getText());

        reservationCRUD rsc = new reservationCRUD();
        Reservation rs = new Reservation(resNbPlace, resId_offre, resIdClient, resPointDepart, resPointArrive, resIdConducteur);
        rsc.addReservation(rs);

    }
 @FXML
private void showHistorique(ActionEvent event) throws IOException {
   // Load the FXML file for the "historique" interface
    FXMLLoader loader = new FXMLLoader(getClass().getResource("historique.fxml"));
    try {
        Parent root = loader.load();
    
        // Get the controller for the "historique" interface
        HistoriqueController hc = loader.getController();
        // Pass any necessary data to the controller
       
        // Set the "historique" interface as the root of the scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

    @FXML
    private void delete(ActionEvent event) {
      
        
  reservationCRUD rcd = new reservationCRUD();

       

        if (tfIdRes.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun client supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le client ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            rcd.deleteReservation(Integer.parseInt(tfIdRes.getText()));
            System.out.println("Client supprimé !");
          //  updatetabclient();
        }
    }

        
    


    @FXML
    private void updateReservation(ActionEvent event) {
         try {
        // Load the update interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("update.fxml"));
        Parent root = loader.load();
        
        // Get the controller for the "update" interface
        UpdateController uc = loader.getController();
        
        // Pass any necessary data to the controller
        // uc.setData(data);
        
        // Set the "update" interface as the root of the scene
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
        
    }
    
    
    // Twilio account SID and auth token
    private static final String ACCOUNT_SID = "your_account_sid_here";
    private static final String AUTH_TOKEN = "your_auth_token_here";
    
    // Twilio phone number
    private static final String TWILIO_NUMBER = "+1415xxxxxxx";
    
    // Send an SMS message to confirm a reservation
    public void sendConfirmationSMS(Reservation reservation) {
        
        // Initialize the Twilio API client with your account SID and auth token
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        
        // Construct the message body
        String messageBody = "Dear " + reservation.getCustomerName() + ", "
                + "Your reservation for " + reservation.getNumberOfPeople() + " people on "
                + reservation.getDate() + " at " + reservation.getTime() + " has been confirmed. "
                + "Thank you for choosing our restaurant!";
        
        // Send the message to the customer's phone number
       Message message = Message.creator(
    new com.twilio.type.PhoneNumber(reservation.getCustomerPhone()),
    new com.twilio.type.PhoneNumber("+21627161394"),
    messageBody)
    .create();


        
        // Print the message SID to the console
        System.out.println(message.getSid());
    }
}
