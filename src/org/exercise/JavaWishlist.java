package org.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class JavaWishlist {
    private final static String FILEPATH = "./resources/wishlist.txt";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        if (checkFile()) {
            System.out.println("Lista Esistente: ************");
            loadFile();
        }
        int choice ;
        ArrayList<String> lista = new ArrayList<>();
        do {

            System.out.print("Aggiungi un regalo: Digita 1 per si e 0 per terminare:  ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Regalo: ");
                    lista.add(scanner.nextLine().trim());
                    Collections.sort(lista);
                    System.out.println("Lunghezza della lista :"+ lista.size());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }

        } while (choice != 0);
        if (!lista.isEmpty()) {
            System.out.println("Lista Regali: ***************");
            for (String item : lista) {
                System.out.print(item + '\n');
                writeFile(item);
            }
        } else {
            System.out.println("Lista vuota!");
        }
    }

    private static boolean checkFile() {
        File file = new File(JavaWishlist.FILEPATH);
        if(file.exists() && file.length() > 0){
            return true;
        }
        return false;
    }

    private static void writeFile(String gift) throws IOException {

        try (FileWriter writer = new FileWriter(FILEPATH, true)) {
            writer.append(gift);
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file" + e.getMessage());
        }

    }

    private static void loadFile() throws FileNotFoundException{
        try (Scanner scanner = new Scanner(new File(FILEPATH))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }

    }
}
