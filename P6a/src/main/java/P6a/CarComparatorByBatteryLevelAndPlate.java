package P6a;

import java.util.Comparator;

public class CarComparatorByBatteryLevelAndPlate implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        float bateriaUno = 0.0F, bateriaDos = 0.0F;

        if(o1 instanceof ElectricCar) bateriaUno = ((ElectricCar)o1).getBatteryCharge();
        if(o1 instanceof HybridCar) bateriaUno = ((HybridCar)o1).getBatteryCharge();
        if(o2 instanceof ElectricCar) bateriaDos = ((ElectricCar)o2).getBatteryCharge();
        if(o2 instanceof HybridCar) bateriaDos = ((HybridCar)o2).getBatteryCharge();

        if(bateriaUno == bateriaDos) return ((Car)o1).compareTo((Car)o2);
        return (int)(bateriaUno - bateriaDos);
    }
}
