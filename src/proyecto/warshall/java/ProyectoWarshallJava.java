/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.warshall.java;

import java.io.IOException;

/**
 *
 * @author retr0
 */
public class ProyectoWarshallJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Warshall w = new Warshall();
        System.out.println("\n\n");
        w.matrizCaminos(3);
    }
    
}
