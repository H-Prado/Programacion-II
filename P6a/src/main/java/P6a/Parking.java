package P6a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Parking {
    private char maxZone;
    private int siteZone;
    private char lowerElectricZone;
    private HashSet<CarSpace> busyCarSpaces = new HashSet<CarSpace>();; // = new String[Character.getNumericValue(maxZone)][siteZone-1];
    private TreeSet<CarSpace> freeCarSpaces = new TreeSet<CarSpace>();

    public Parking(char maxZone, char siteZone) {
        this.maxZone = maxZone;
        this.siteZone = siteZone;
    }

    public char getMaxZone() {
        return maxZone;
    }

    public void setMaxZone(char maxZone) {
        this.maxZone = maxZone;
    }

    public int getSiteZone() {
        return siteZone;
    }

    public void setSiteZone(char siteZone) {
        this.siteZone = siteZone;
    }

    public Parking(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String entrada;
        boolean inicio = true;
        String[] partes;

        //Inicia el parking a null
        while (scanner.hasNextLine() && inicio){
            entrada = scanner.nextLine();
            if(!entrada.startsWith("#") && !entrada.isEmpty()){
                partes = entrada.split(";");
                this.maxZone = entrada.charAt(0);
                this.siteZone = Integer.parseInt(partes[1]);
                this.lowerElectricZone = partes[2].charAt(0);
                //this.carSpaces = new CarSpace[((int)(maxZone))-64][siteZone];
//                System.out.println("MZ: "+maxZone+" SZ: "+siteZone+" LEZ: "+lowerElectricZone+" CSM: "+ carSpaces.length + "x"+carSpaces[0].length);
                for(int x = 0; x < (int)lowerElectricZone-64; x++){
                    for(int y = 0; y<siteZone-1; y++) {
                        this.freeCarSpaces.add(new CarSpace(new Coordinate((char)(x+65),y+1), null, null));
//                        System.out.println(carSpaces[x][y].toText());
                    }
                }
                for(int x = (int)lowerElectricZone-65; x < ((int)maxZone)-65; x++){
                    for(int y = 0; y<siteZone-1; y++) {
                        this.freeCarSpaces.add(new ElectricCarSpace(new Coordinate((char)(x+65),y+1), null, null));
//                        System.out.println(carSpaces[x][y].toText());
                    }
                }
                inicio = false;
            }
        }
        //Añade los coches al parking
        while(scanner.hasNextLine()){
            entrada = scanner.nextLine();
            if (!entrada.startsWith("#") && !entrada.isEmpty()){
                partes = entrada.split(";");
                char letraPlaza = partes[0].charAt(0);
                int numeroPlaza = Integer.parseInt(partes[0].substring(1,partes[0].length()));
                this.freeCarSpaces.remove(new CarSpace(new Coordinate(letraPlaza,numeroPlaza), null,null));
                this.busyCarSpaces.add(new CarSpace(new Coordinate(letraPlaza,numeroPlaza), partes[1], partes[2]));
            }
        }
    }

    public void saveParking (String filename) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(filename));
        printWriter.println(maxZone + ";" + siteZone + ";" + lowerElectricZone);

        TreeSet<CarSpace> parkingOrdenado = new TreeSet<CarSpace>(new CarSpaceComparatorByTimeAndCoordinate());
        parkingOrdenado.addAll(busyCarSpaces);

        for(CarSpace c : parkingOrdenado){
            printWriter.println(c.getCoordinate().toText() + ";" + c.getPlate() + ";" + c.getEntryTime());
        }

        printWriter.close();
    }

    public void carEntry (Car car, String time){
        ElectricCarSpace electricCarSpaceAux;
        Coordinate coordinateAux;

        if(car instanceof CombustionCar) {
            busyCarSpaces.add(new CarSpace(freeCarSpaces.first().getCoordinate(), car.getPlate(), time));
            freeCarSpaces.remove(freeCarSpaces.first());
        }
        else{
            coordinateAux  = freeCarSpaces.higher(new CarSpace(new Coordinate(lowerElectricZone, 0), null, null)).getCoordinate(); //ponemos el 0 porque es el siguiente, no incluido el 0!!!
            electricCarSpaceAux = new ElectricCarSpace(coordinateAux, car.getPlate(), time);
            electricCarSpaceAux.estadoON();
            busyCarSpaces.add(electricCarSpaceAux);
            freeCarSpaces.remove(new CarSpace(coordinateAux, null, null));
        }
    }

    public String carDeparture (String plate){
        String timeRetorno = "";

        for(CarSpace c : busyCarSpaces){
            if(c.getPlate().matches(plate)){
                freeCarSpaces.add(new CarSpace(c.getCoordinate(), null, null));
                busyCarSpaces.remove(c);
                return c.getEntryTime();
            }
        }
        return null;
    }

    public String toMap(){
        String mapa = "";
        char zonaElectrica = lowerElectricZone;
        boolean plazasElectricas = false;
        Coordinate coordinateAnterior = new Coordinate();
        TreeSet<CarSpace> treeDibujo = new TreeSet<CarSpace>();

        treeDibujo.addAll(busyCarSpaces);
        treeDibujo.addAll(freeCarSpaces);

        for(CarSpace c : treeDibujo){
            if(c.getCoordinate().getZone() == lowerElectricZone) plazasElectricas = true;
            if(plazasElectricas) {
                if (c.getCoordinate().getZone() == coordinateAnterior.getZone()) mapa = mapa + c.getCoordinate().toText() + " E " + c.getPlate() + " |";
                else mapa = mapa + "\n" + c.getCoordinate().toText() + " E " + c.getPlate() + " |";
            }
            else{
                if (c.getCoordinate().getZone() == coordinateAnterior.getZone()) mapa = mapa + c.getCoordinate().toText() + "   " + c.getPlate() +  " |";
                else mapa = mapa + "\n" + c.getCoordinate().toText() + "   " + c.getPlate() + " |";
            }
            coordinateAnterior = c.getCoordinate();
        }
        return mapa;
    }
}
