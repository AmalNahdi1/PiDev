/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.historique_reservation;
import entities.payement;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.historique_reservationCRUD;
import services.payementCRUD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class HistoriqueController implements Initializable {

    @FXML
    private TableView<historique_reservation> tbHistorique;
    @FXML
    private Label historiqueRes;

    @FXML
    private TableColumn<?, ?> id_reservation;
    @FXML
    private TableColumn<?, ?> id_historique_reservation;
    @FXML
    private TableColumn<?, ?> id_conducteur;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> date_depart_reelle;
    @FXML
    private TableColumn<?, ?> date_arrive_reelle;
    @FXML
    private TableColumn<?, ?> lieu_depart;
    @FXML
    private TableColumn<?, ?> lieu_destination;
    @FXML
    private TableColumn<?, ?> avis_client;
    @FXML
    private TableColumn<?, ?> status_reservation;
    @FXML
    private TableColumn<?, ?> id_client;
    @FXML
    private Button btnBack;
    @FXML
    private TextField tfIdRes;
    @FXML
    private TextField tfDepRelle;
    @FXML
    private TextField tfArriveReelle;
    @FXML
    private TextField tfIdConducteur;
    @FXML
    private TextField tfLieuDestination;
    @FXML
    private TextField tfAvisClient;
    @FXML
    private TextField tfStatus;
    @FXML
    private TextField tfIdClient;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeHistorique();
    
    }

    public void listeHistorique() {
        System.out.println("sdfsdf");
        historique_reservationCRUD hrc = new historique_reservationCRUD();

        id_reservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        id_historique_reservation.setCellValueFactory(new PropertyValueFactory<>("id_historique_reservation"));
        id_conducteur.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date_depart_reelle.setCellValueFactory(new PropertyValueFactory<>("date_depart_reelle"));
        date_arrive_reelle.setCellValueFactory(new PropertyValueFactory<>("date_arrive_reelle"));
        lieu_depart.setCellValueFactory(new PropertyValueFactory<>("lieu_depart"));
        lieu_destination.setCellValueFactory(new PropertyValueFactory<>("lieu_destination"));
        avis_client.setCellValueFactory(new PropertyValueFactory<>("avis_client"));
        status_reservation.setCellValueFactory(new PropertyValueFactory<>("status_reservation"));
        id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));

        List<historique_reservation> list = hrc.historique_reservationList();
        System.out.println(list);
        tbHistorique.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        // Load the FXML file for the "historique" interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
        try {
            Parent root = loader.load();
            // Get the controller for the "historique" interface
            // ReservationController rc = loader.getController();
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
   private void save(ActionEvent event) {
    if (tfIdRes.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Aucun id choisi !");
        alert.showAndWait();
        return;
    }
    
    int Id_reservation = Integer.parseInt(tfIdRes.getText());
    String Depart_reelle = tfDepRelle.getText();
    String Arrive_reelle = tfArriveReelle.getText();
    int Id_conducteur = Integer.parseInt(tfIdConducteur.getText());
    String Lieu_destination = tfLieuDestination.getText();
    String Avis_client = tfAvisClient.getText();
    String Status = tfStatus.getText();
    int Id_client = Integer.parseInt(tfIdClient.getText());
    
    historique_reservationCRUD rsc = new historique_reservationCRUD();
    historique_reservation hr = new historique_reservation(
             Id_reservation,Depart_reelle, Arrive_reelle, Id_conducteur , Lieu_destination , Avis_client , Status ,Id_client);
    rsc.addHistorique_Reservation(hr);
    
}

    }


