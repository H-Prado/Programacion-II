package P4;

import java.io.ObjectStreamException;

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

    @Override
    public boolean equals(Object c){
        return (this.number == ((Coordinate)c).number && this.zone == ((Coordinate)c).zone);
    }

    public String toText(){
        return String.valueOf(this.zone)+this.number;
    }
}
