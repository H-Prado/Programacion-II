package P5b;

public abstract class Car {
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
    public String toString(){
        if(this instanceof CombustionCar) return ("C;" + this.getPlate() + ";" + this.getManufacturer() + ";" + this.getTotalPower());
        if(this instanceof ElectricCar) return ("E;" + this.getPlate() + ";" + this.getManufacturer() + ";" + this.getTotalPower() + ";" + ((ElectricCar)this).getBatteryCharge());
        else return ("H;" + this.getPlate() + ";" + this.getManufacturer() + ";" + ((HybridCar)this).getMechanicalPower() + ";" + ((HybridCar)this).getElectricPower() + ";" + ((HybridCar)this).getBatteryCharge());
    }

    ;
}
