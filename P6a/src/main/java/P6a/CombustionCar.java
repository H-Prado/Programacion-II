package P6a;

public class CombustionCar extends Car {
    private static final String PLATE_FORMAT = "^\\d{4}[A-Z]{3}$";
    private static final long serialVersionUID = 2650292707495938005L;
    private int mechanicalPower;

    public CombustionCar() {
        super();
    }

    public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
        super(plate, manufacturer);
        this.mechanicalPower = mechanicalPower;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTotalPower() {
        return this.mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        this.mechanicalPower = mechanicalPower;
    }

    public boolean isValidMechanicalPower(int mechanicalPower){
        if(mechanicalPower <= 500 && mechanicalPower >= 60){
            return true;
        }
        else{
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
