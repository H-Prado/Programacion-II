package P3;

public class CombustionCar {
    private static final float maxBatteryCharge = 100.0F;
    private static final float minBatteryCharge = 0.0F;
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";
    private static int maxMechanicalPower = 500;
    private static int minMechanicalPower = 60;
    private String plate;
    private String manufacturer;
    private int mechanicalPower;

    public CombustionCar() {
    }

    public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
        this.manufacturer = manufacturer;
        if(isValidPlate(plate))this.plate = plate;
        else System.out.println("Formato de matrícula errónea");
        if(isValidMechanicalPower(mechanicalPower)) this.mechanicalPower = mechanicalPower;
        else System.out.println("Formato de potencia eléctrica errónea");
    }

    public boolean isValidMechanicalPower(int mechanicalPower){
        if(mechanicalPower <= maxMechanicalPower && mechanicalPower >= minMechanicalPower){
            return true;
        }
        else{
            System.out.println("Incorrect power value: " + mechanicalPower + ". Valid range is " + minMechanicalPower + "-" + maxMechanicalPower);
            return false;
        }
    }

    public boolean isValidPlate(String plate){
        if(plate.matches(PLATE_FORMAT)){
            return true;
        }
        else{
            System.out.println("Incorrect plate value: " + mechanicalPower + ".Not comply with format \"DDDDLLL\"");
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

    public int getMechanicalPower() {
        return mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        if(isValidMechanicalPower(this.mechanicalPower)) this.mechanicalPower = mechanicalPower;
    }

    public static int getMaxMechanicalPowerr() {
        return maxMechanicalPower;
    }

    public static void setMaxMechanicalPower(int maxMechanicalPower) {
        CombustionCar.maxMechanicalPower = maxMechanicalPower;
    }

    public static int getMinMechanicalPower() {
        return minMechanicalPower;
    }

    public static void setMinMechanicalPower(int minMechanicalPower) {
        CombustionCar.minMechanicalPower = minMechanicalPower;
    }

    public String toText()
    {
        return("E;" + this.plate + ";" + this.manufacturer + ";" + this.mechanicalPower);
    }
}
