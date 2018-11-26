/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.warshall.java;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author retr0
 */
public class Warshall {
    private int[][] mtxAdy;
    private int mtxSize;
    
    public Warshall() throws IOException{
        mtxSize = cantElemts();
        mtxAdy = new int[mtxSize][];
        for(int i = 0; i < mtxSize; i++)
            mtxAdy[i] = new int[mtxSize];
        if(!esCuadrada())
            throw new IOException("La matriz no es cuadrada, por favor revise el archivo\n");
        leerArchivo();
        System.out.println("Matriz de adyacencia: ");
        printMtx();
        
    }
    
    public void leerArchivo() throws FileNotFoundException, IOException{
        String cadena;
        FileReader file = new FileReader("matriz.txt");
        BufferedReader buffer = new BufferedReader(file);
        int i = 0;
        while((cadena = buffer.readLine()) != null){
            int j = 0;
            for(int k = 0; k < cadena.length(); k++)
                if(cadena.charAt(k) != ' '){
                    mtxAdy[i][j] = cadena.charAt(k)-'0';
                    j++;
                }
            i++;
        }
    }
    
    private int cantElemts() throws FileNotFoundException, IOException{
        String cadena;
        FileReader file = new FileReader("matriz.txt");
        BufferedReader buffer = new BufferedReader(file);
        cadena = buffer.readLine();
        int cant = 0;
        for(int i = 0; i < cadena.length(); i++){
            if(cadena.charAt(i) != ' ')
                cant++;
        }
        return cant;
    }
    
    private boolean esCuadrada() throws FileNotFoundException, IOException{
        String cadena;
        FileReader file = new FileReader("matriz.txt");
        BufferedReader buffer = new BufferedReader(file);
        int numFilas = 0;
        int numColum = cantElemts();
        while((cadena = buffer.readLine()) != null){
            numFilas++;
            
            int cantColumInThisFila = 0;
            for(int i = 0; i < cadena.length(); i++)
                if(cadena.charAt(i) != ' ')
                    cantColumInThisFila++;
            
            if(cantColumInThisFila != numColum)
                return false;
        }
        if(numFilas != numColum)
            return false;
        else
            return true;
    }
    
    public void printMtx(){
        for(int i = 0; i < mtxSize; i++){
            for(int j = 0; j < mtxSize; j++)
                System.out.print((mtxAdy[i][j]) + " ");
            System.out.println();
        }       
    }
    
    public void printMtx(int[][] mtx){
        for(int i = 0; i < mtx.length; i++){
            for(int j = 0; j < mtx.length; j++)
                System.out.print((mtx[i][j]) + " ");
            System.out.println();
        }       
    }
    
    public int[][] matrizCaminos(int n){
        int[][]result = mtxAdy;
        
        int[][]anterior = mtxAdy;
        for(int i = 0; i < n; i++){
            LinkedList<Thread> hilos = new LinkedList<Thread>();
            for(int row = 0; row < mtxSize; row++)
                for(int col = 0; col < mtxSize; col++){
                    /*for(int j = 0; j < mtxSize; j++){
                        if((anterior[row][col] == 1) || ((anterior[row][i] == 1) && (anterior[i][col] == 1)))
                            result[row][col] = 1;
                    }*/
                    Thread hilo = new Thread(new HiloMultiplicador(anterior, result, row, col));
                    hilos.add(hilo);
                    hilo.start();
                }
            for(Thread hilo: hilos){
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   
            }
            anterior = result;
        }
        
        System.out.println("\nMatriz de caminos final: ");
        printMtx(result);
        return result;
    }
}
