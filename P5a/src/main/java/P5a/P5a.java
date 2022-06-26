package P5a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P5a {
    static Parking miparking;
    static CarDB cdb = new CarDB();


    public static void main (String[] args) throws FileNotFoundException {
        String file1 = args[0], file2 = args[1], file3 = args[2], file4 = args[3], file5 = args[4];

        //Creamos la base de datos de los coches a partir de cityCars
        cdb.readCityCarsFile(file4);
        System.out.println("Total power =" + cdb.computeTotalPower());
        System.out.println("median Battery Charge Leve = " + cdb.computeAverageBatteryLevel());

        miparking = new Parking(file1);
        new P5a().processIO(file2);
        miparking.saveParking(file3);
        System.out.println(miparking.toMap());
        cdb.saveCarsToFile(file5);
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
                        miparking.carEntry(cdb.getCarFromPlate(partes[1]), partes[2]);
                        break;
                    case "O":
                        miparking.carDeparture(partes[1]);
                        break;
                }
            }
        }
    }
}
