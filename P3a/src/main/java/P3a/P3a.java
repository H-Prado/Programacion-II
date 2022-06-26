package P3a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class P3a {
    static ArrayList<ElectricCar> electricCityCars = new ArrayList<ElectricCar>(25);
    static ArrayList<CombustionCar> combustionCityCars = new ArrayList<CombustionCar>(25);
    static ArrayList<HybridCar> hybridCityCars = new ArrayList<HybridCar>(25);

    public static void main(String[] args) throws FileNotFoundException {
        String plate3="6288LYM", manufacturer3="Ford"; int power3=110; float batteryCharge3=120.5F;
        String plate4="07451JMR", manufacturer4="Seat"; int power4=60; float batteryCharge4=10.1F;
        String plate5="3400JXK", manufacturer5="Peugeot"; int power5=70; float batteryCharge5=50F;
        String plate6="0041LDR", manufacturer6="Seat"; int power6=20; float batteryCharge6=10.1F;

        ElectricCar coche1 = new ElectricCar("1111KLS","SEAT",220,30.5F);
        System.out.println(coche1.toText());
        if(new ElectricCar().isValidPlate("2222LSX") && new ElectricCar().isValidElectricPower(220) && new ElectricCar().isValidBatteryCharge(30.5F))
        {
            ElectricCar coche2 = new ElectricCar();
            coche2.setPlate("2222LSX");
            coche2.setManufacturer("FORD");
            coche2.setElectricPower(220);
            coche2.setBatteryCharge(30.5F);
            System.out.println(coche2.toText());
        }
        if(new ElectricCar().isValidPlate(plate3) && new ElectricCar().isValidElectricPower(power3) && new ElectricCar().isValidBatteryCharge(batteryCharge3))
        {
            ElectricCar coche3 = new ElectricCar(plate3, manufacturer3, power3, batteryCharge3);
            System.out.println(coche3.toText());
        }
        if(new ElectricCar().isValidPlate(plate4) && new ElectricCar().isValidElectricPower(power4) && new ElectricCar().isValidBatteryCharge(batteryCharge4))
        {
            ElectricCar coche4 = new ElectricCar(plate4, manufacturer3, power4, batteryCharge4);
            System.out.println(coche4.toText());
        }
        if(new ElectricCar().isValidPlate(plate5) && new ElectricCar().isValidElectricPower(power5) && new ElectricCar().isValidBatteryCharge(batteryCharge5))
        {
            ElectricCar coche5 = new ElectricCar(plate5, manufacturer3, power5, batteryCharge5);
            System.out.println(coche5.toText());
        }
        if(new ElectricCar().isValidPlate(plate6) && new ElectricCar().isValidElectricPower(power6) && new ElectricCar().isValidBatteryCharge(batteryCharge6))
        {
            ElectricCar coche6 = new ElectricCar(plate6, manufacturer3, power6, batteryCharge6);
            System.out.println(coche6);
        }

        //Probamos método readCityCarsFile
        new P3a().readCityCarsFile("cityCars.txt");

        //Probamso el metodo int computeTotalPower
        System.out.println("\nPotencia total = " + new P3a().computeTotalPower());
        
        //Probamos el método increaseBatteryChargeLevel
        new P3a().writeElectricCityCarsFile("ElectricCarsOutput.txt");

    }

    public void readCityCarsFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String partes[];
        int x = 0;
        while (scanner.hasNextLine()) {
            partes = scanner.nextLine().split(";");
            switch (partes[0]) {
                case "E":
                    electricCityCars.add(new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]), parseFloat(partes[4].replace(",","."))));
                    break;
                case "H":
                    hybridCityCars.add(new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]), Integer.parseInt(partes[4]), parseFloat(partes[5].replace(",","."))));
                    break;
                case "C":
                    combustionCityCars.add(new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3])));
            }
        }
    }
    
    public int computeTotalPower(){
        int totalPower = 0;
        int x = 0;

        for(x = 0; x<electricCityCars.size(); x++){
            totalPower = electricCityCars.get(x).getElectricPower() + totalPower;
        }
        for(x = 0; x<combustionCityCars.size(); x++){
            totalPower = combustionCityCars.get(x).getMechanicalPower() + totalPower;
        }
        for(x = 0; x<hybridCityCars.size(); x++){
            totalPower = hybridCityCars.get(x).getElectricPower() + hybridCityCars.get(x).getMechanicalPower() + totalPower;
        }
        return totalPower;
    }

    public static void writeElectricCityCarsFile (String filename) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File (filename));
        String partes[];
        ElectricCar electricAux;
        int x;
        System.out.println("\nMétodo writeElectricCityCarsFile ejecutado");

        for(x = 0; x<electricCityCars.size(); x++){
            electricAux = electricCityCars.get(x);
            electricAux.increaseBatteryLevelCharge(10F);
            printWriter.println(electricAux.toText());
        }
        printWriter.close();
    }
}
