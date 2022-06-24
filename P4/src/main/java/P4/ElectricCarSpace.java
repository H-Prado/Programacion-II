package P4;

public class ElectricCarSpace extends CarSpace{
    private ElectricCharger charger;

    public ElectricCarSpace(Coordinate coordinate, String plate){
        super(coordinate, plate);
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
