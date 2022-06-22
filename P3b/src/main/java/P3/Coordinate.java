package P3;

public class Coordinate {
    private char zone;
    private int number;

    public Coordinate() {
    }

    public Coordinate(char zone, int number) {
        this.zone = zone;
        this.number = number;
    }

    public char getZone() {
        return zone;
    }

    public void setZone(char zone) {
        this.zone = zone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number > 0) this.number = number;
    }

    public boolean isEqualTo(Coordinate c){
        return (this.number == c.number && this.zone == c.zone);
    }

    public String toText(){
        return String.valueOf(this.zone)+this.number;
    }
}
