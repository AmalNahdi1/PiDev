package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.price;
import interfaces.PriceInterface;

public class Price implements PriceInterface {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    public price getPaymentByDepartFin(String depart, String fin) {
        price price = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/autoxpress", "username",
                    "password");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM prices WHERE depart = ? AND arrivee =? ");
            ps.setString(1, depart);
            ps.setString(1, fin);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = new price(rs.getString("depart"), rs.getString("depart"), rs.getFloat("price"));

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

}
