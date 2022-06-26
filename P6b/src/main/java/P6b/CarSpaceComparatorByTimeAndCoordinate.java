package P6b;

import java.util.Comparator;

public class CarSpaceComparatorByTimeAndCoordinate implements Comparator<CarSpace> {
    @Override
    public int compare(CarSpace o1, CarSpace o2) {
        if(o1.getEntryTime().matches(o2.getEntryTime())) return o1.getCoordinate().compareTo(o2.getCoordinate());

        int hUno = Integer.parseInt(o1.getEntryTime().split(":")[0].trim());     //hora entrada
        int mUno = Integer.parseInt(o1.getEntryTime().split(":")[1].trim());     //minuto entrada
        int hDos = Integer.parseInt(o2.getEntryTime().split(":")[0].trim());    //hora salida
        int mDos = Integer.parseInt(o2.getEntryTime().split(":")[1].trim());    //minuto salida
        return (hUno*60+mUno)-(hDos*60+mDos);
    }
}
