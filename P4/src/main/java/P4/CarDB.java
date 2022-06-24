package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class CarDB {
    private ArrayList<Car> cityCars = new ArrayList<Car>(100);



    public float computeAverageBatteryLevel(){
        int x = 0, contador = 0;
        float averageBattery = 0.0F;

        for(x = 0; x<cityCars.size(); x++) {
            if (cityCars.get(x) instanceof ElectricCar || cityCars.get(x) instanceof HybridCar){
                averageBattery = cityCars.get(x).getTotalPower() + averageBattery;
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
}
