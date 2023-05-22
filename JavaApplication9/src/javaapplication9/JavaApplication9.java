/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author User
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class JavaApplication9 {

    static ArrayList<Punct> puncte;
    static int nrTotalPuncte;
    static int nrPuncteTraseu;
    
    static int[] sol, curent, abs, ord;
    static int distantaSol = Integer.MAX_VALUE;
    
    public static int calcTotal(){
        int d = 0;
        for(int i = 1; i < nrPuncteTraseu; i++){
            int indexP1 = curent[i-1]-1;
            int indexP2 = curent[i]-1;
            d += calcDistEucl(abs[indexP1], ord[indexP1], abs[indexP2], ord[indexP2]);
        }
        return d;
    }
    
    public static boolean verifica(int poz){
        for(int i = 0; i < poz; i++){
            for(int j = i+1; j <= poz; j++){
                if(curent[i] == curent[j]){
                    return false;
                }
            }
        }
       return true;
    }
    
    public static void bkt(int poz){
        System.out.println(poz);
        if(poz == nrPuncteTraseu){
            int distanta = calcTotal();
            if(distanta < distantaSol){
                distantaSol = distanta;
                for(int i = 0; i < nrPuncteTraseu; i++){
                    sol[i] = curent[i];
                }
            }
            return;
        }
        
        for(int i = 1; i <= nrTotalPuncte; i++){
            curent[poz] = i;
            if(verifica(poz)){
                bkt(poz+1);
            }
        }
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Tastati numele fisierului de intrare(fără extensie):");
        Scanner scanner = new Scanner(System.in);
        String numeFisier = scanner.nextLine();
        System.out.println("Nume fisier intrare: " + numeFisier + ".gtsp");

        try {
            System.out.println("--- Date citite");

            //citim datele din fisier
            Scanner scannerFisier = new Scanner(new File("./seturiTEST/" + numeFisier + ".gtsp"));
            //ignoram primele 3 linii
            for (int i = 0; i < 3; i++) {
                scannerFisier.nextLine();
            }
                       // System.out.println("da");
            //obtinem nr de puncte printr-un split al urmatoarei linii
            String linieCitita = scannerFisier.nextLine();
            String[] subsiruri = linieCitita.split(":");
            nrTotalPuncte = Integer.parseInt(subsiruri[1].trim());
            System.out.println("x: " + nrTotalPuncte);

            for (int i = 0; i < 3; i++) {
                scannerFisier.nextLine();
            }

            //citim punctele
            abs = new int[nrTotalPuncte];
            ord = new int[nrTotalPuncte];

            for (int i = 0; i < nrTotalPuncte; i++) {
                int nrOrdine = scannerFisier.nextInt();
                int x = scannerFisier.nextInt();
                int y = scannerFisier.nextInt();
                abs[i] = x;
                ord[i] = y;
                
            }

            System.out.println("--- Sfarsit");
        } catch (FileNotFoundException ex) {
            System.out.println("Eroare citire fisier");
        }

        //citim p de la tastatura
        System.out.println("Tastati valoarea lui p:");
        int p = scanner.nextInt();
        System.out.println("p: " + p);

        //nrPuncteTraseu = n
        //nrTotalPuncte = x
        nrPuncteTraseu = nrTotalPuncte * p / 100;
        System.out.println("n: " + nrPuncteTraseu);

        sol = new int[nrPuncteTraseu];
        curent = new int[nrPuncteTraseu];
        bkt(0);
        
        System.out.println("D: " + distantaSol);
        for(int i : sol){
            System.out.print(i + " ");
        }
        }
    
    
        public static int calcDistEucl(int x1, int y1, int x2, int y2){
            int deltax = x1 - x2;
            int deltay = y1 - y2;
            return (int) (Math.sqrt(deltax*deltax + deltay*deltay)+0.5);
        }
    
    }
