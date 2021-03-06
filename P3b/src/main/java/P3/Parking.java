package P3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Parking {
    private char maxZone;
    private int siteZone;
    private char lowerElectricZone;
    private CarSpace[][] carSpaces; // = new String[Character.getNumericValue(maxZone)][siteZone-1];

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

        while (scanner.hasNextLine() && inicio){
            entrada = scanner.nextLine();
            if(!entrada.startsWith("#") && !entrada.isEmpty()){
                partes = entrada.split(";");
                this.maxZone = entrada.charAt(0);
                this.siteZone = Integer.parseInt(partes[1]);
                this.lowerElectricZone = partes[2].charAt(0);
                this.carSpaces = new CarSpace[((int)(maxZone))-64][siteZone];
//                System.out.println("MZ: "+maxZone+" SZ: "+siteZone+" LEZ: "+lowerElectricZone+" CSM: "+ carSpaces.length + "x"+carSpaces[0].length);
                for(int x = 0; x<carSpaces.length; x++){
                    for(int y = 0; y<carSpaces[0].length; y++) {
                        this.carSpaces[x][y] = new CarSpace(new Coordinate((char)(x+65),y+1), null);
//                        System.out.println(carSpaces[x][y].toText());
                    }
                }
                inicio = false;
            }
        }


        while(scanner.hasNextLine()){
            entrada = scanner.nextLine();
            if (!entrada.startsWith("#") && !entrada.isEmpty()){
                partes = entrada.split(";");
                char letraPlaza = partes[0].charAt(0);
                int numeroPlaza = Integer.parseInt(partes[0].substring(1,partes[0].length()));
                this.carSpaces[(int)(letraPlaza)-65][numeroPlaza-1] = new CarSpace(new Coordinate(letraPlaza,numeroPlaza), partes[1]);
            }
        }
    }

    public void saveParking (String filename) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File(filename));
        printWriter.println(maxZone + ";" + siteZone + ";" + lowerElectricZone);

        for(int x = 0; x<carSpaces.length; x++){
            for(int y = 0; y<carSpaces[0].length; y++) {
                if(carSpaces[x][y].getPlate() != null) printWriter.println(carSpaces[x][y].getCoordinate().toText() + ";" + carSpaces[x][y].getPlate());
            }
        }
        printWriter.close();
    }

    public void carEntry (String plate, char Type){
        if(Type != 'C') {
            for (int x = (int)lowerElectricZone-65; x < carSpaces.length; x++) {
                for (int y = 0; y < carSpaces[0].length; y++) {
                    if (carSpaces[x][y].getPlate() == null) {
                        carSpaces[x][y] = new CarSpace(carSpaces[x][y].getCoordinate(), plate);
                        return;
                    }
                }
            }
        }
        else{
            for (int x = 0; x < (int)lowerElectricZone-64; x++) {
                for (int y = 0; y < carSpaces[0].length; y++) {
                    if (carSpaces[x][y].getPlate() == null){
                        carSpaces[x][y] = new CarSpace(carSpaces[x][y].getCoordinate(), plate);
                        return;
                    }
                }
            }
        }
    }

    public void carDeparture (String plate){
        for(int x = 0; x<carSpaces.length; x++){
            for(int y = 0; y<carSpaces[0].length; y++) {
                if(carSpaces[x][y].getPlate() != null && carSpaces[x][y].getPlate().matches(plate)) {
                    carSpaces[x][y] = new CarSpace(carSpaces[x][y].getCoordinate(), null);
                    return;
                }
            }
        }
    }

    public String toMap(){
        String mapa = "";
        for(int x = 0; x<(int) (lowerElectricZone - 65); x++) {
            mapa = mapa + "\n";
            for (int y = 0; y < carSpaces[0].length; y++) {
                if (carSpaces[x][y].getPlate() != null) mapa = mapa + carSpaces[x][y].getCoordinate().toText() + "   " + carSpaces[x][y].getPlate() + "|";
                else mapa = mapa + carSpaces[x][y].getCoordinate().toText() + "          |";
            }
        }
        for(int x = (int) (lowerElectricZone - 65); x< carSpaces.length; x++){
            mapa = mapa + "\n";
            for(int y = 0; y<carSpaces[0].length; y++) {
                if(carSpaces[x][y].getPlate() != null) mapa = mapa + carSpaces[x][y].getCoordinate().toText() + " E " + carSpaces[x][y].getPlate() + "|";
                else mapa = mapa + carSpaces[x][y].getCoordinate().toText() + " E        |";
            }
        }
        return mapa;
    }
}
