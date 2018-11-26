/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.warshall.java;

/**
 *
 * @author retr0
 */
class HiloMultiplicador implements Runnable
{
	private int[][] m1;
	private int[][] resultado;
	private int fila;
	private int columna;
	
	/**
	 * Guarda los parámetros que se le pasan 
	 * @param m1 primer operando
	 * @param m2 segundo operando
	 * @param resultado matriz donde dejar el resultado
	 * @param fila fila que debe calcular
	 * @param columna columna que debe calcular
	 */
	public HiloMultiplicador (int[][] m1, int[][]resultado, int fila, int columna)
	{
		this.m1 = m1;
		this.resultado = resultado;
		this.fila = fila;
		this.columna = columna;
	}

	/**
	 * Calcula el elemento fila,columna de la matriz resultado
	 */
	public void run()
	{
		for (int i=0;i<m1.length;i++){
                    //System.out.println(resultado[fila][columna] + " or (" + resultado[fila][i] + " and " + resultado[i][columna] + ")");
                    if((m1[fila][columna] == 1) || ((m1[fila][i] == 1) && (m1[i][columna] == 1)))
                        resultado[fila][columna] = 1;
                }
	}
}