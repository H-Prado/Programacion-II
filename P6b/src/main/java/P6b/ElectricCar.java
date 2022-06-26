package P6b;

public class ElectricCar extends Car {
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";

    private static final long serialVersionUID = 2314952153963792566L;
    private int electricPower;
    private float batteryCharge;
    private final static float BATTERY_CAPACITY = 100.0F;

    public ElectricCar() {
    }

    public ElectricCar(String plate, String manufacturer, int electricPower, float batteryCharge) {
        super(plate, manufacturer);
        this.electricPower = electricPower;
        this.batteryCharge = batteryCharge;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public int getTotalPower() {
        return electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower = electricPower;
    }

    public float getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(float batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public void increaseBatteryLevelCharge (float chargeTime){
        float bateriaAux = this.batteryCharge + ((chargeTime * (new ElectricCharger().getPower())) / BATTERY_CAPACITY) * 100;
        if(bateriaAux < 100.0F) this.batteryCharge = bateriaAux;
        else this.batteryCharge = 100.0F;
    }

    public boolean isValidElectricPower(int electricPower){
        if(electricPower <= 800 && electricPower >= 50){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidBatteryCharge(float batteryCharge) {
        if (batteryCharge <= 100.0F && batteryCharge >= 0.0F) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPlate(String plate){
        if(plate.matches(PLATE_FORMAT)){
            return true;
        }
        else{
            return false;
        }
    }
}
