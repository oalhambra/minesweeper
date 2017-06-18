package main;

import java.util.Random;

/**
 * Created by espur on 08/06/2017.
 */


/**
 * valor desconocido = -2
 * puesto vacio      = -1
 * mina              = 00
 * otro numero       = minas alrededor de casilla
 */
public class Tablero {
    private int[][] tablero;
    private int[][] tableroDescubierto;
    private int alto;
    private int ancho;
    private int numMinas;
    public Tablero(int alto, int ancho, int numMinas){
        this.alto=alto;
        this.ancho=ancho;
        this.numMinas=numMinas;
        tablero =new int[alto][ancho];
        tableroDescubierto =new int[alto][ancho];
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                tablero[i][j]=-1;
                tableroDescubierto[i][j]=-2;
            }
        }
        initTablero(3,2);
    }
    public int[][] getTablero(){
        return tablero;
    }
    public int[][] getTableroDescubierto() {
        return tableroDescubierto;
    }
    public void printTableroTexto(int tablero[][]){
        System.out.print("     ");
        for(int i=0;i<ancho;i++){
            System.out.print(String.format("%02d ",i));
        }
        System.out.print("\n");
        System.out.print("     ");
        for(int i=0;i<ancho;i++){
            System.out.print("---");
        }
        System.out.print("\n");
        for (int i=0;i<alto;i++){
            System.out.print(String.format("%02d ",i)+"| ");
            for (int j=0;j<ancho;j++){
                System.out.print(String.format("%02d ", tablero[i][j]));
            }
            System.out.print(" |"+String.format("%02d ",i));
            System.out.print("\n");
        }
        System.out.print("     ");
        for(int i=0;i<ancho;i++){
            System.out.print("---");
        }
        System.out.print("\n");
        System.out.print("     ");
        for(int i=0;i<ancho;i++){
            System.out.print(String.format("%02d ",i));
        }
        System.out.print("\n");
    }
    private void initTablero(int posAlto,int posAncho){
        Random random=new Random(System.currentTimeMillis());
        int minasTablero=0;
        int posx, posy;
        int contadorFallos=0;
        while (minasTablero<numMinas){
            posx=random.nextInt(alto);
            posy=random.nextInt(ancho);
            if(!((posAlto==posx)&&(posAncho==posy))){
                if(tablero[posx][posy]==-1){
                    if(comprobarAlrededoresPlus(posx, posy)){
                        tablero[posx][posy]=0;
                        minasTablero++;
                        contadorFallos=0;
                    }
                    else{
                        contadorFallos++;
                    }
                }
            }
            if(contadorFallos==150){
                System.out.println("No se ha podido generar el tablero");
                System.exit(-1);
            }
        }
        crearNumeros();
        activaCasilla(posAlto, posAncho);
    }
    private boolean comprobarAlrededoresPlus(int posx, int posy){
        tablero[posx][posy]=88;
        boolean posible=true;
        int posActualx, posActualy;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                posActualx=posx+i;
                posActualy=posy+j;
                if((posActualx>=0&&posActualy>=0)&&(posActualx<alto&&posActualy<ancho)){
                    posible=posible&&comprobarAlrededores(posActualx,posActualy);
                }
            }
        }



        tablero[posx][posy]=-1;
        return posible;
    }
    private boolean comprobarAlrededores(int posx, int posy){
        boolean posible=false;
        int posActualx, posActualy;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if(i==0&&j==0){
                    continue;
                }
                posActualx=posx+i;
                posActualy=posy+j;
                if((posActualx>=0&&posActualy>=0)&&(posActualx<alto&&posActualy<ancho)){
                    if(tablero[posActualx][posActualy]==-1){
                        posible=true;
                    }
                }
            }
        }

        return posible;
    }
    private void crearNumeros(){
        for(int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                if(tablero[i][j]==-1){
                    minasAlrededores(i,j);
                }
            }
        }
    }
    private void minasAlrededores(int posx, int posy){
        int posActualx, posActualy;
        int contador=0;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if(i==0&&j==0){
                    continue;
                }
                posActualx=posx+i;
                posActualy=posy+j;
                if((posActualx>=0&&posActualy>=0)&&(posActualx<alto&&posActualy<ancho)){
                    if(tablero[posActualx][posActualy]==0){
                        contador++;
                    }
                }
            }
        }
        if(contador!=0){
            tablero[posx][posy]=contador;
        }
        if(contador==8){
            System.out.print("hola\n");
        }
    }
    public void activaCasilla(int posx, int posy){
        if(tablero[posx][posy]==0){
            System.out.println("Game over");
            System.out.print("  _____\n" +
                             " /     \\\n" +
                             "| () () |\n" +
                             " \\  ^  /\n" +
                             "  |||||\n" +
                             "  |||||");
        }
        else if(tablero[posx][posy]==-1){
            clearArea(posx,posy);
        }
        else{
            clearSquare(posx,posy);
        }
    }
    private void clearSquare(int posx, int posy){
        tableroDescubierto[posx][posy]=tablero[posx][posy];
    }
    private void clearArea(int posx, int posy){
        int posActualx, posActualy;

        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                posActualx=posx+i;
                posActualy=posy+j;
                if((posActualx>=0&&posActualy>=0)&&(posActualx<alto&&posActualy<ancho)){
                    if(i==0&&j==0){
                        clearSquare(posx, posy);
                    }
                    else if((tablero[posActualx][posActualy]==-1)&&(tableroDescubierto[posActualx][posActualy]==-2)){
                        clearArea(posActualx, posActualy);
                    }
                    else {
                        clearSquare(posActualx, posActualy);
                    }
                }




            }
        }

    }
}
