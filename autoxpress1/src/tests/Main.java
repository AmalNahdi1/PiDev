/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import services.reservationCRUD;
import entities.Reservation;
import entities.historique_reservation;
import entities.payement;
import java.sql.Date;
import utils.myConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import services.historique_reservationCRUD;
import services.payementCRUD;

/**
 *
 * @author amal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

       // call the updateReservation method to update the corresponding record in the database
myConnection con = new myConnection();
//reservationCRUD ecd = new reservationCRUD();
/*

Reservation re = new Reservation();
re.setId_conducteur(1);
re.setNb_place(2);
re.setId_offre(22);
re.setId_client(23);
re.setPoint_de_depart("aryana");
re.setPoint_arrive("esprit");
re.setId_conducteur(88);
int id = 2;
String newDate = "2023-08-01";
String newTime = "14:00";
//ecd.updateReservation(re);
//ecd.deleteReservation(1);

        System.out.println("updated");
        historique_reservationCRUD hrc = new historique_reservationCRUD();
historique_reservation h1 = new historique_reservation();

h1.setId_reservation(1);
h1.setDate_depart_reelle("a");
h1.setDate_arrive_reelle("a");
h1.setId_conducteur(22);
h1.setLieu_depart("esb");
h1.setLieu_destination("Aryana");
h1.setAvis_client("nice");
h1.setStatus_reservation("CONFIRMED");
h1.setId_client(123);

hrc.addHistorique_Reservation(h1);
System.out.println("Historique Reservation added successfully.");

    }*/
payementCRUD prc = new payementCRUD();
payement payment = new payement();
payment.setPaymentId(4);
payment.setClientId(2);
payment.setModePayment("Credit Card");
payment.setPrixCourse(1000.0f);

 prc.addPayment(payment);
 
 payement paymentToUpdate = new payement();
paymentToUpdate.setPaymentId(1);
paymentToUpdate.setClientId(2);
paymentToUpdate.setModePayment("Cash");
paymentToUpdate.setPrixCourse(1200.0f);
prc.updatePayement(paymentToUpdate, "1");


prc.deletePayment(4); 




}

}
