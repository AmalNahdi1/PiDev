package entities;

public class price {
    private int priceId;
    private String depart;
    private String arrivee;
    private float prixCourse;

    public price() {
    }

    public price(String depart, String arrivee, float prixCourse) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.prixCourse = prixCourse;
    }

    public String getArrivee() {
        return arrivee;
    }

    public String getDepart() {
        return depart;
    }

    public int getPriceId() {
        return priceId;
    }

    public float getPrixCourse() {
        return prixCourse;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public void setPrixCourse(float prixCourse) {
        this.prixCourse = prixCourse;
    }
}
