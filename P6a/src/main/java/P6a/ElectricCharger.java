package P6a;

public class ElectricCharger {
    private boolean connected;
    private static final int POWER = 25;

    public ElectricCharger(){
        this.connected = false;
    }

    public void connect(){
        this.connected = true;
    }

    public void disconnect(){
        this.connected = false;
    }
    public boolean estado(){
        if(connected) return true;
        else return false;
    }

    public int getPower(){
        return POWER;
    }
}
