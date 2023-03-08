package interfaces;

import entities.price;

public interface PriceInterface<P> {

    public price getPaymentByDepartFin(String depart, String fin);

}
