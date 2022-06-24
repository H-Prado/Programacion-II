package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class P4 {
    static Parking parking;
    static CarDB cdb = new CarDB();


    public static void main (String[] args) throws FileNotFoundException {
        String file1 = args[0], file2 = args[1], file3 = args[2];

        //Creamos la base de datos de los coches a partir de cityCars
        cdb.readCityCarsFile("cityCars.txt");
        System.out.println("Total power =" + cdb.computeTotalPower());
        System.out.println("median Battery Charge Leve = " + cdb.computeAverageBatteryLevel());

        parking = new Parking(file1);
        new P4().processIO(file2);
        parking.saveParking(file3);
        System.out.println(parking.toMap());
    }

    public void processIO (String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        String[] partes;
        String entrada;

        while (scanner.hasNextLine()) {
            entrada = scanner.nextLine();
            if (!entrada.startsWith("#")) {
                partes = entrada.split(";");
                switch (partes[0]){
                    case "I":
                        parking.carEntry(cdb.getCarFromPlate(partes[1]));
                        break;
                    case "O":
                        parking.carDeparture(cdb.getCarFromPlate(partes[1]));
                        break;
                }
            }
        }
    }
}
