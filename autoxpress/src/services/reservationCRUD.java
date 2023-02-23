/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import interfaces.reservationInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.myConnection;

/**
 *
 * @author amal
 */
public class reservationCRUD implements reservationInterface<Reservation> {
    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public void updateReservation(Reservation reservation, String id) {
        String sql = "UPDATE reservation SET nb_place = ?, id_offre = ?, id_client = ?, point_de_depart = ?, point_arrive = ?, id_conducteur = ? WHERE id_reservation = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getNb_place());
            pstmt.setInt(2, reservation.getId_offre());

            pstmt.setInt(3, reservation.getId_client());
            pstmt.setString(4, reservation.getPoint_de_depart());
            pstmt.setString(5, reservation.getPoint_arrive());
            pstmt.setInt(6, reservation.getId_conducteur());
            pstmt.setString(7, id);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("La réservation a été mise à jour avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete a reservation from the database
    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE id_reservation = ? ";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
            System.out.println("reservation supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List entitiesList() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public void addReservation(Reservation r) {
        String sql = "INSERT INTO reservation(id_reservation, nb_place, id_offre, id_client, point_de_depart, point_arrive, id_conducteur) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, r.getId_reservation());
            pstmt.setInt(2, r.getNb_place());
            pstmt.setInt(3, r.getId_offre());
            pstmt.setInt(4, r.getId_client());
            pstmt.setString(5, r.getPoint_de_depart());
            pstmt.setString(6, r.getPoint_arrive());
            pstmt.setInt(7, r.getId_conducteur());
            pstmt.executeUpdate();
            System.out.println("La réservation a été ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
