package P3;

import com.sun.source.tree.Scope;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P3b {

    public static void main(String[] args) throws FileNotFoundException {
        String file1 = args[0], file2 = args[1], file3 = args[2], entrada;
        String[] partes;

        Parking parking = new Parking(file1);

        Scanner scanner = new Scanner(new File(file2));
        while (scanner.hasNextLine()){
            entrada = scanner.nextLine();
            partes = entrada.split(";");
            if(partes[0].matches("I")) {
                parking.carEntry(partes[1], partes[2].charAt(0));
            }
            if(partes[0].matches("O")){
                parking.carDeparture(partes[1]);
            }
        }

        parking.saveParking(file3);

        System.out.println(parking.toMap());

    }
}
