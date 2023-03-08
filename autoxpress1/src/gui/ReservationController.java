/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
import entities.client;
import entities.historique_reservation;
import interfaces.clients;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.Clients;
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
    private ComboBox<Integer> tfIdClient;

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
    @FXML
    private Button btnPayer;
    @FXML
    private TextField numerotel;
    @FXML
    private Button btnmeteo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getclients();
    }

    @FXML
    private void confirmer(ActionEvent event) {

        int resNbPlace = Integer.parseInt(tfNombrePlaces.getText());
        int resId_offre = Integer.parseInt(tfIdOffre.getText());
        int resIdClient = tfIdClient.getValue();
        String resPointDepart = tfPointDepart.getText();
        String resPointArrive = tfPointArrive.getText();
        int resIdConducteur = Integer.parseInt(tfIdConcucteur.getText());

        reservationCRUD rsc = new reservationCRUD();
        Reservation rs = new Reservation(resNbPlace, resId_offre, resIdClient,
                resPointDepart, resPointArrive,
                resIdConducteur);
        rsc.addReservation(rs);
        
        String num="+216"+numerotel.getText();
        System.out.println(num);
        String message = String.format("Autoxpress : Cher "+tfIdClient.getValue()+", Votre réservation de"+  Integer.parseInt(tfNombrePlaces.getText())+"places a été confirmée.  de"+tfPointDepart.getText() +"vers "+tfPointArrive.getText()+"avec le conducteur d ID"+Integer.parseInt(tfIdConcucteur.getText()));
        rsc.send(num,message);

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
            // updatetabclient();
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

    private void getclients() {
        Clients hrc = new Clients();
        List<client> list = hrc.getAll();
        System.out.println(list);
        for (client c : list) {
            tfIdClient.getItems().add(c.getId_client());

        }

        // Set the list of client names as the data source for the combo box

    }

    @FXML
    private void payer(ActionEvent event) {
        try {
            // Load the payement interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payement.fxml"));
            Parent root = loader.load();

            // Get the controller for the "update" interface
            PayementController pc = loader.getController();

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

    @FXML
    private void meteo(ActionEvent event) {
        try {
            // Load the update interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("meteo.fxml"));
            Parent root = loader.load();

            // Get the controller for the "update" interface
           MeteoController mc = loader.getController();

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
}}
