package P3a;

public class HybridCar {
    private static final float maxBatteryCharge = 100.0F;
    private static final float minBatteryCharge = 0.0F;
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";
    private static int maxElectricPower = 100;
    private static int minElectricPower = 20;
    private static int maxMechanicalPower = 500;
    private static int minMechanicalPower = 60;
    private String plate;
    private String manufacturer;
    private int electricPower;
    private int mechanicalPower;
    private float batteryCharge;

    public HybridCar() {
    }

    public HybridCar(String plate, String manufacturer, int mechanicalPower, int electricPower, float batteryCharge) {
        this.manufacturer = manufacturer;
        if(isValidPlate(plate))this.plate = plate;
        if(isValidElectricPower(electricPower)) this.electricPower = electricPower;
        if(isValidMechanicalPower(mechanicalPower)) this.electricPower = mechanicalPower;
        if(isValidBatteryCharge(batteryCharge)) this.batteryCharge = batteryCharge;
    }

    public boolean isValidElectricPower(int electricPower){
        if(electricPower <= maxElectricPower && electricPower >= minElectricPower){
            return true;
        }
        else{
            System.out.println("Incorrect power value: " + electricPower + ". Valid range is " + minElectricPower + "-" + maxElectricPower);
            return false;
        }
    }

    public boolean isValidMechanicalPower(int mechanicalPower){
        if(mechanicalPower <= maxMechanicalPower && mechanicalPower >= minMechanicalPower){
            return true;
        }
        else{
            System.out.println("Incorrect power value: " + electricPower + ". Valid range is " + minElectricPower + "-" + maxElectricPower);
            return false;
        }
    }

    public boolean isValidBatteryCharge(float batteryCharge) {
        if (batteryCharge <= maxBatteryCharge && batteryCharge >= minBatteryCharge) {
            return true;
        } else {
            System.out.println("Incorrect battery charge value: " + batteryCharge + ". Valid range is " + minBatteryCharge + "-" + maxBatteryCharge);
            return false;
        }
    }

    public boolean isValidPlate(String plate){
        if(plate.matches(PLATE_FORMAT)){
            return true;
        }
        else{
            System.out.println("Incorrect plate value: " + electricPower + ".Not comply with format \"DDDDLLL\"");
            return false;
        }
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        if(isValidPlate(plate)) this.plate = plate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(int electricPower) {
        if(isValidElectricPower(electricPower)) this.electricPower = electricPower;
    }
    public static int getMaxMechanicalPower() {
        return maxMechanicalPower;
    }

    public static void setMaxMechanicalPower(int maxMechanicalPower) {
        HybridCar.maxMechanicalPower = maxMechanicalPower;
    }

    public static int getMinMechanicalPower() {
        return minMechanicalPower;
    }

    public static void setMinMechanicalPower(int minMechanicalPower) {
        HybridCar.minMechanicalPower = minMechanicalPower;
    }

    public int getMechanicalPower() {
        return mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        if(isValidMechanicalPower(mechanicalPower)) this.mechanicalPower = mechanicalPower;
    }

    public float getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(float batteryCharge) {
        if(isValidBatteryCharge(batteryCharge)) this.batteryCharge = batteryCharge;
    }

    public static int getMaxElectricPower() {
        return maxElectricPower;
    }

    public static void setMaxElectricPower(int maxElectricPower) {
        HybridCar.maxElectricPower = maxElectricPower;
    }

    public static int getMinElectricPower() {
        return minElectricPower;
    }

    public static void setMinElectricPower(int minElectricPower) {
        HybridCar.minElectricPower = minElectricPower;
    }

    public String toText()
    {
        return("E;" + this.plate + ";" + this.manufacturer + ";" + this.mechanicalPower + ";" + this.electricPower + ";" + this.batteryCharge);
    }
    public void increaseBatteryLevelCharge (float increment){
        this.batteryCharge = this.batteryCharge + increment;
    }
}
