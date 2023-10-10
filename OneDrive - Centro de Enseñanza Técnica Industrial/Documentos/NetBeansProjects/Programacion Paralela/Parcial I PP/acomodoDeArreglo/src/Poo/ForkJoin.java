
package Poo;

import java.util.concurrent.RecursiveAction;
import Poo.Merge; 
import java.util.concurrent.ForkJoinPool;

public class ForkJoin extends RecursiveAction {
    
    //Instancias
    private int[] arreglo;
    private int izq; 
    private int der;

    //Constructor
    public ForkJoin(int[] arreglo, int izq, int der) {
        this.arreglo = arreglo;
        this.izq = izq;
        this.der = der;
    }

    @Override
    protected void compute() {
        if (izq < der) { //Verificamos si el subarreglo de la izquierda es menos para 
            int medio = (izq + der) / 2; //Punto medio del subarreglo
            
            //Intsnacias ForkJoin para representar los subarreglos 
            //Fork/Join Framework distribuya automáticamente las tareas en diferentes hilos de ejecución disponibles
            ForkJoin izquierda = new ForkJoin(arreglo, izq, medio);
            ForkJoin derecha = new ForkJoin(arreglo, medio + 1, der);

            //Metodo invokeAll para ejecutar las tareas de ordenación de manera paralela
            invokeAll(izquierda, derecha);

            Merge.merge(arreglo, izq, medio, der);
        }
    }
    
    //Iniciar el proceso de ordenación
    public static void ordenarConForkJoin(int[] arreglo) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(); //piscina de hilos
        ForkJoin forkJoin = new ForkJoin(arreglo, 0, arreglo.length - 1); //arreglo inicio y fin del subarreglo completo.
        forkJoinPool.invoke(forkJoin);// Inicia la ejecución paralela del algoritmo 
    }
}
