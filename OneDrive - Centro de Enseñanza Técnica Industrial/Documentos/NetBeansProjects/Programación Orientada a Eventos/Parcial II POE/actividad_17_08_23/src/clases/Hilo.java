
package clases;


public class Hilo extends Thread{
    
    private int[][] matriz; //Se declara un arreglo bidemencional 
    private int[] columnas; //Arreglo
    private int escalar;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int columna : columnas) { //foreach recorre los elementos del arreglo y +
                //+ asigna el valor del elemento en el arreglo columnas y asigna el valor a columna
                matriz[i][columna] *= escalar; // *= escalarmultiplica el valor actual por el escalar en el constructor
            }
        }
    }
    
    public Hilo(int[][] matriz, int[] columnas, int escalar) {
        this.matriz = matriz; //Referencia a la matriz que se multiplicara por el hilo
        this.columnas = columnas; //Un arreglo que el hilo va a multiplicar
        this.escalar = escalar;
    }
    
}
