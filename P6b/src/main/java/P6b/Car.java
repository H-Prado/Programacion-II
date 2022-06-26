package P6b;

public abstract class Car implements Comparable{
    private static final long serialVersionUID = 7999008895737219541L;
    private String plate;
    private String manufacturer;

    protected Car() {
    }

    protected Car(String plate, String manufacturer) {
        this.plate = plate;
        this.manufacturer = manufacturer;
    }
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract int getTotalPower();

    @Override
    public int compareTo(Object o) {
        return this.plate.compareTo(((Car)o).getPlate());
    }

//    Orden matricula vida real (Primero letras, luego numeros, orden ascendente
//    @Override
//    public int compareTo(Object o) {
//        if(this.plate.substring(4,7).matches(((Car)o).getPlate().substring(4,7)))
//            return Integer.parseInt(this.plate.substring(0,3))-Integer.parseInt(((Car)o).getPlate().substring(0,3));
//        return this.getPlate().substring(4,6).compareTo(((Car)o).getPlate().substring(4,6));
//    }

    @Override
    public String toString(){
        if(this instanceof CombustionCar) return ("C;" + this.getPlate() + ";" + this.getManufacturer() + ";" + this.getTotalPower());
        if(this instanceof ElectricCar) return ("E;" + this.getPlate() + ";" + this.getManufacturer() + ";" + this.getTotalPower() + ";" + ((ElectricCar)this).getBatteryCharge());
        else return ("H;" + this.getPlate() + ";" + this.getManufacturer() + ";" + ((HybridCar)this).getMechanicalPower() + ";" + ((HybridCar)this).getElectricPower() + ";" + ((HybridCar)this).getBatteryCharge());
    }

    ;
}
