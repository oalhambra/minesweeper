package main;

import java.util.Random;

/**
 * Created by espur on 08/06/2017.
 */
public class Tablero {
    private int[][] tablero;
    private int alto;
    private int ancho;
    private int numMinas;
    public Tablero(int alto, int ancho, int numMinas){
        this.alto=alto;
        this.ancho=ancho;
        this.numMinas=numMinas;
        tablero =new int[alto][ancho];
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                tablero[i][j]=-1;
            }
        }
        initTablero(3,2);
    }
    public void printTableroTexto(){
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
        while (minasTablero<numMinas){
            posx=random.nextInt(alto);
            posy=random.nextInt(ancho);
            if((posAlto!=posx)&&(posAncho!=posy)){
                if(tablero[posx][posy]==-1){
                    if(comprobarAlrededores(posx, posy)){
                        tablero[posx][posy]=1;
                        minasTablero++;
                    }
                }
            }
        }
    }
    private boolean comprobarAlrededores(int posx, int posy){
        boolean posible=false;
        int posActualx, posActualy;
        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if(i==0&j==0){
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
}
