package P6a;

public class CarSpace implements Comparable{
    private Coordinate coordinate;
    private String plate = null;
    private String entryTime;
    public CarSpace() {
    }

    public CarSpace(Coordinate coordinate, String plate, String entryTime) {
        this.coordinate = coordinate;
        this.plate = plate;
        this.entryTime = entryTime;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getPlate() {
        if(plate == null) return "       ";
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String toText(){
        if(this.plate!=null) return coordinate.toText() + ";" + this.plate + ";" + this.entryTime;
        else return coordinate.toText();
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public boolean equals(Object o){
        return this.coordinate.equals(o);
    }

    @Override
    public int compareTo(Object o) {
        return this.coordinate.compareTo(((CarSpace)o).getCoordinate());

    }
}
