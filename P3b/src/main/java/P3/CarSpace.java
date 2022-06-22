package P3;

public class CarSpace {
    private Coordinate coordinate;
    private String plate = null;

    public CarSpace() {
    }

    public CarSpace(Coordinate coordinate, String plate) {
        this.coordinate = coordinate;
        this.plate = plate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String toText(){
        if(this.plate!=null) return coordinate.toText() + ";" + this.plate;
        else return coordinate.toText();
    }
}
