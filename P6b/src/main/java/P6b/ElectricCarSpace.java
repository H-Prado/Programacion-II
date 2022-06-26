package P6b;

public class ElectricCarSpace extends CarSpace {
    private ElectricCharger charger;

    public ElectricCarSpace(Coordinate coordinate, String plate, String entryTime){
        super(coordinate, plate, entryTime);
        this.charger = new ElectricCharger();
    }
    public boolean getEstado(){
        return this.charger.estado();
    }
    public void estadoON(){
        this.charger.connect();
    }
    public void estadoOFF(){
        this.charger.disconnect();
    }
}
