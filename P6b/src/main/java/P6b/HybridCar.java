package P6b;

public class HybridCar extends Car {
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";
    private static final long serialVersionUID = 7558906366093469276L;
    private int electricPower;
    private int mechanicalPower;
    private float batteryCharge;

    public HybridCar() {
        super();
    }

    public HybridCar(String plate, String manufacturer, int mechanicalPower, int electricPower, float batteryCharge) {
        super(plate, manufacturer);
        this.electricPower = electricPower;
        this.mechanicalPower = mechanicalPower;
        this.batteryCharge = batteryCharge;
    }

    public int getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower = electricPower;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getMechanicalPower() {
        return this.mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        this.mechanicalPower = mechanicalPower;
    }

    public int getTotalPower() {
        return this.mechanicalPower + this.electricPower;
    }

    public float getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(float batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public void increaseBatteryLevelCharge (float increment){
        this.batteryCharge = this.batteryCharge + increment;
    }

    public boolean isValidElectricPower(int electricPower){
        if(electricPower <= 350 && electricPower >= 20){
            return true;
        }
        else{
            System.out.println("Potencia electrica erronea");
            return false;
        }
    }

    public boolean isValidMechanicalPower(int mechanicalPower){
        if(mechanicalPower <= 500 && mechanicalPower >= 60){
            return true;
        }
        else{
            System.out.println("Potencia mecánica erronea");
            return false;
        }
    }

    public boolean isValidBatteryCharge(float batteryCharge) {
        if (batteryCharge <= 100.0F && batteryCharge >= 0.0F) {
            return true;
        } else {
            System.out.println("Carga bateria erronea");
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
