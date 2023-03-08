/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.client;
import entities.historique_reservation;
import interfaces.clients;
import interfaces.historique_reservationInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amal
 */

public class Clients implements clients<client> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";
    private final String SELECT_ALL_QUERY = "SELECT * FROM client";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Close the database connection, statement, and result set
    private void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet)
            throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public ArrayList<client> getAll() {
        ArrayList<client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                client H = new client();
                H.setId_client(rs.getInt("id_client"));
                H.setNom_client(rs.getString("nom_client"));
                H.setPrenom_client(rs.getString("prenom_client"));

                clients.add(H);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

}
