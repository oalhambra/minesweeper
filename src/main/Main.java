package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tablero tablero=new Tablero(10,10, 10);
//        tablero.printTableroTexto(tablero.getTablero());
//        Scanner scanner=new Scanner(System.in);
//        int x=scanner.nextInt();
//        int y=scanner.nextInt();
//        tablero.activaCasilla(x,y);
        tablero.printTableroTexto(tablero.getTableroDescubierto());
    }
}
