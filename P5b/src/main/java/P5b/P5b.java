package P5b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P5b {
    static Parking miparking;
    static CarDB cdb = new CarDB();


    public static void main (String[] args) throws FileNotFoundException {
        String file1 = args[0], file2 = args[1], file3 = args[2], file4 = args[3], file5 = args[4], file6 = args[5];

        //Creamos la base de datos de los coches a partir de cityCars
        cdb.readCityCarsFile(file4);
        System.out.println("Total power =" + cdb.computeTotalPower());
        System.out.println("median Battery Charge Level = " + cdb.computeAverageBatteryLevel());

        miparking = new Parking(file1);
        new P5b().processIO(file2);
        System.out.println(miparking.toMap());

        miparking.saveParking(file3);

        cdb.saveCarsToFile(file5);
        PrintWriter printWriter = new PrintWriter(new File(file6));
        printWriter.println(miparking.toMap());
        printWriter.close();
    }

    public void processIO (String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String[] partes;
        String entrada, tiempoEntrada, tiempoSalida;
        Car carAux;

        while (scanner.hasNextLine()) {
            entrada = scanner.nextLine();
            if (!entrada.startsWith("#")) {
                partes = entrada.split(";");
                carAux = cdb.getCarFromPlate(partes[1]);
                carAux.toString();
                switch (partes[0]){
                    case "I":
                        tiempoEntrada = partes[2];
                        miparking.carEntry(carAux, tiempoEntrada);
                        break;
                    case "O":
                        tiempoEntrada = miparking.carDeparture(carAux.getPlate());
                        if(carAux instanceof ElectricCar || carAux instanceof HybridCar){
                            tiempoSalida = partes[2];
                            cdb.increaseBatteryChargeLevel(carAux.getPlate(), tiempoEntrada, tiempoSalida);}
                        break;
                }
            }
        }
    }
}
