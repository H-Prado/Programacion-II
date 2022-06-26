package P6b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Float.parseFloat;

public class CarDB {
    LinkedHashMap<String, Car> cityCars = new LinkedHashMap<String, Car>();
    private ArrayList<Car> cityCarsAux;

    public float computeAverageBatteryLevel(){
        int x = 0, contador = 0;
        float averageBattery = 0.0F;

        for(Map.Entry<String, Car> carAux : cityCars.entrySet()){
            if (carAux.getValue() instanceof ElectricCar){
                averageBattery = ((ElectricCar)carAux.getValue()).getBatteryCharge() + averageBattery;
                contador++;
            }
            if(carAux.getValue() instanceof HybridCar){
                averageBattery = ((HybridCar)carAux.getValue()).getBatteryCharge() + averageBattery;
                contador++;
            }
        }
        return (averageBattery/contador);
    }


    public int computeTotalPower(){
        int x = 0, totalPower = 0;

        for(Map.Entry<String, Car> carAux : cityCars.entrySet()) {
            totalPower = carAux.getValue().getTotalPower() + totalPower;
        }
        return totalPower;
    }


    public void readCityCarsFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String partes[];
        int  potenciaAux, potenciaAuxDos;
        float bateriaAux;
        String plateAux;

        int x = 0;
        while (scanner.hasNextLine()) {
            partes = scanner.nextLine().split(";");
            plateAux = partes[1];
            switch (partes[0]) {
                case "E":
                    potenciaAux = Integer.parseInt(partes[3]);
                    bateriaAux = parseFloat(partes[4].replace(",","."));
                    if(new ElectricCar().isValidPlate(partes[1]) &&
                            new ElectricCar().isValidElectricPower(potenciaAux) &&
                            new ElectricCar().isValidBatteryCharge(bateriaAux)){
                    cityCars.put(plateAux,(new ElectricCar(partes[1], partes[2], potenciaAux, bateriaAux)));}
                    break;
                case "H":
                    potenciaAux = Integer.parseInt(partes[3]);
                    potenciaAuxDos = Integer.parseInt(partes[4]);
                    bateriaAux = parseFloat(partes[5].replace(",","."));
                    if(new HybridCar().isValidPlate(partes[1]) &&
                            new HybridCar().isValidElectricPower(potenciaAux) &&
                            new HybridCar().isValidMechanicalPower(potenciaAuxDos) &&
                            new HybridCar().isValidBatteryCharge(bateriaAux)) {
                        cityCars.put(plateAux,(new HybridCar(partes[1], partes[2], potenciaAux, potenciaAuxDos, bateriaAux)));
                    }break;
                case "C":
                    potenciaAux = Integer.parseInt(partes[3]);
                    if(new CombustionCar().isValidPlate(partes[1]) &&
                            new CombustionCar().isValidMechanicalPower(potenciaAux)){
                        cityCars.put(plateAux,(new CombustionCar(partes[1], partes[2], potenciaAux)));}
            }
        }
    }

    public Car getCarFromPlate(String plate){
        return cityCars.get(plate);
    }

    public void saveCarsToFile(String filename) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File (filename));

        for(Map.Entry<String, Car> carAux : cityCars.entrySet()){
            printWriter.println(carAux.getValue().toString());
        }
        printWriter.close();
    }

    public void increaseBatteryChargeLevel(String plate, String entryTime, String departureTime){
        Car carAux = getCarFromPlate(plate);
        if (carAux instanceof ElectricCar) ((ElectricCar)carAux).increaseBatteryLevelCharge(intervalInHours(entryTime,departureTime));
        if (carAux instanceof HybridCar) ((HybridCar)carAux).increaseBatteryLevelCharge(intervalInHours(entryTime,departureTime));
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
        cityCarsAux = new ArrayList<Car>(cityCars.values());


//        Imprimimos la lista no ordenada
        System.out.println("\n\nLista NO ordenada:");
        for(int x = 0; x < cityCarsAux.size(); x++) System.out.println(cityCarsAux.get(x));
        Collections.sort(cityCarsAux);
//        Imprimimos la lista ordenada por bateria y luego por matricula
        System.out.println("\n\nLista ordenada por MATRÍCULA:");
        for(int x = 0; x < cityCarsAux.size(); x++) System.out.println(cityCarsAux.get(x));


    }

    public void sortByBatteryChargeAndPlate(){
        CarComparatorByBatteryLevelAndPlate comparador = new CarComparatorByBatteryLevelAndPlate();

//        Dos siguientes lineas para verificar ordenes
//        cityCars.put("0678FGH",(new HybridCar("9678ABC", "Alfa Romeo", 150, 90, 57.5F)));
//        cityCars.put("123LGP",(new HybridCar("1233LGP", "Alfa Romeo", 150, 90, 57.5F)));
        cityCarsAux = new ArrayList<Car>(cityCars.values());

//        Imprimimos la lista no ordenada
//        System.out.println("\n\nLista NO ordenada:");
//        for(int x = 0; x < cityCarsAux.size(); x++) System.out.println(cityCarsAux.get(x));

//        Imprimimos la lista ordenada por bateria y luego por matricula
        Collections.sort(cityCarsAux,comparador);
        System.out.println("\n\nLista ordenada por BATERÍA y luego por MATRÍCULA:");
        for(int x = 0; x < cityCarsAux.size(); x++) System.out.println(cityCarsAux.get(x));

    }
}