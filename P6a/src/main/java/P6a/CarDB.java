package P6a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class CarDB {
    private ArrayList<Car> cityCars = new ArrayList<Car>();

    public float computeAverageBatteryLevel(){
        int x = 0, contador = 0;
        float averageBattery = 0.0F;

        for(x = 0; x<cityCars.size(); x++) {
            if (cityCars.get(x) instanceof ElectricCar){
                averageBattery = ((ElectricCar)cityCars.get(x)).getBatteryCharge() + averageBattery;
                contador++;
            }
            if(cityCars.get(x) instanceof HybridCar){
                averageBattery = ((HybridCar)cityCars.get(x)).getBatteryCharge() + averageBattery;
                contador++;
            }
        }
        return (averageBattery/contador);
    }


    public int computeTotalPower(){
        int x = 0, totalPower = 0;

        for(x = 0; x<cityCars.size(); x++){
            totalPower = cityCars.get(x).getTotalPower() + totalPower;
        }
        return totalPower;
    }


    public void readCityCarsFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String partes[];
        int  potenciaAux, potenciaAuxDos;
        float bateriaAux;

        int x = 0;
        while (scanner.hasNextLine()) {
            partes = scanner.nextLine().split(";");
            switch (partes[0]) {
                case "E":
                    potenciaAux = Integer.parseInt(partes[3]);
                    bateriaAux = parseFloat(partes[4].replace(",","."));
                    if(new ElectricCar().isValidPlate(partes[1]) &&
                            new ElectricCar().isValidElectricPower(potenciaAux) &&
                            new ElectricCar().isValidBatteryCharge(bateriaAux)){
                    cityCars.add(new ElectricCar(partes[1], partes[2], potenciaAux, bateriaAux));}
                    break;
                case "H":
                    potenciaAux = Integer.parseInt(partes[3]);
                    potenciaAuxDos = Integer.parseInt(partes[4]);
                    bateriaAux = parseFloat(partes[5].replace(",","."));
                    if(new HybridCar().isValidPlate(partes[1]) &&
                            new HybridCar().isValidElectricPower(potenciaAux) &&
                            new HybridCar().isValidMechanicalPower(potenciaAuxDos) &&
                            new HybridCar().isValidBatteryCharge(bateriaAux)) {
                        cityCars.add(new HybridCar(partes[1], partes[2], potenciaAux, potenciaAuxDos, bateriaAux));
                    }break;
                case "C":
                    potenciaAux = Integer.parseInt(partes[3]);
                    if(new CombustionCar().isValidPlate(partes[1]) &&
                            new CombustionCar().isValidMechanicalPower(potenciaAux)){
                        cityCars.add(new CombustionCar(partes[1], partes[2], potenciaAux));}
            }
        }
    }

    public Car getCarFromPlate(String plate){
        for(int x = 0; x < cityCars.size(); x++){
            if(cityCars.get(x).getPlate().matches(plate)) return cityCars.get(x);
        }
        return null;
    }

    public void saveCarsToFile(String filename) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File (filename));

        for(int x = 0; x < cityCars.size(); x++){
            printWriter.println(cityCars.get(x).toString());
        }
        printWriter.close();
    }

    public void increaseBatteryChargeLevel(String plate, String entryTime, String departureTime){
        Car car = getCarFromPlate(plate);
        if (car instanceof ElectricCar) ((ElectricCar)car).increaseBatteryLevelCharge(intervalInHours(entryTime,departureTime));
        if (car instanceof HybridCar) ((HybridCar)car).increaseBatteryLevelCharge(intervalInHours(entryTime,departureTime));
    }

    private float intervalInHours(String inTime, String outTime) {
        int hi = Integer.parseInt(inTime.split(":")[0].trim());     //hora entrada
        int mi = Integer.parseInt(inTime.split(":")[1].trim());     //minuto entrada
        int ho = Integer.parseInt(outTime.split(":")[0].trim());    //hora salida
        int mo = Integer.parseInt(outTime.split(":")[1].trim());    //minuto salida
        int dif = (ho*60+mo)-(hi*60+mi);        //Convertimos todo a minutos para operar
        return ((float)dif/60);                 //Lo devolvemos en un factor de horas
    }

    public void sortByPlate(){
        Collections.sort(cityCars);
    }

    public void sortByBatteryChargeAndPlate(){
        CarComparatorByBatteryLevelAndPlate comparador = new CarComparatorByBatteryLevelAndPlate();
        Collections.sort(cityCars,comparador);
    }
}