package P3;

public class ElectricCar {
    private static final float maxBatteryCharge = 100.0F;
    private static final float minBatteryCharge = 0.0F;
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";
    private static int maxElectricPower = 800;
    private static int minElectricPower = 50;
    private String plate;
    private String manufacturer;
    private int electricPower;
    private float batteryCharge;

    public ElectricCar() {
    }

    public ElectricCar(String plate, String manufacturer, int electricPower, float batteryCharge) {
        this.manufacturer = manufacturer;
        if(isValidPlate(plate))this.plate = plate;
        else System.out.println("Formato de matrícula errónea");
        if(isValidElectricPower(electricPower)) this.electricPower = electricPower;
        else System.out.println("Formato de potencia eléctrica errónea");
        if(isValidBatteryCharge(batteryCharge)) this.batteryCharge = batteryCharge;
        else System.out.println("Formato de batería errónea");
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
        ElectricCar.maxElectricPower = maxElectricPower;
    }

    public static int getMinElectricPower() {
        return minElectricPower;
    }

    public static void setMinElectricPower(int minElectricPower) {
        ElectricCar.minElectricPower = minElectricPower;
    }

    public void increaseBatteryLevelCharge (float increment){
        this.batteryCharge = this.batteryCharge + increment;
    }

    public String toText()
    {
        return("E;" + this.plate + ";" + this.manufacturer + ";" + this.electricPower + ";" + this.batteryCharge);
    }
}
