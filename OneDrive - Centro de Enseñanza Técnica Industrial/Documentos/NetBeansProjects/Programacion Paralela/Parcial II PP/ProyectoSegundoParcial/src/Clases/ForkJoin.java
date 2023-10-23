
package Clases;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class ForkJoin  extends  RecursiveTask<ResultadoSuma> {
    
    private float[] arreglo;
    private int inicio;
    private int fin;
    
    public ForkJoin(float[] arreglo, int inicio, int fin){
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected ResultadoSuma compute() {
        
        long tiempoInicio = System.nanoTime();
        
        if(fin - inicio <= 1000){
            float suma = 0;
            for(int i = inicio; i <= fin; i++){
                suma += arreglo[i];
            }
            
            long tiempoFin = System.nanoTime(); // Registra el tiempo de finalización
            long tiempoEjecucion = tiempoFin - tiempoInicio; // Calcula el tiempo de ejecución
            
            return new ResultadoSuma(suma, tiempoEjecucion);
            
        }else{
            int medio = (inicio + fin)/2;
            ForkJoin izquierda = new ForkJoin(arreglo, inicio, medio);
            ForkJoin derecha = new ForkJoin(arreglo, medio+1, fin);
            izquierda.fork();
            ResultadoSuma sumDerecha = derecha.compute();
            ResultadoSuma sumIzquierda = izquierda.join();
            
            long tiempoFin = System.nanoTime(); // Registra el tiempo de finalización
            long tiempoEjecucion = tiempoFin - tiempoInicio; // Calcula el tiempo de ejecución

            
            return new ResultadoSuma(sumDerecha.getSuma() + sumIzquierda.getSuma(), tiempoEjecucion);        
        }              
    }
    
        //Iniciar el proceso de ordenación
    public static ResultadoSuma sumarElementosConForkJoin(float[] arreglo) {
        ForkJoinPool pool = new ForkJoinPool(); //piscina de hilos
        ResultadoSuma resultado = pool.invoke(new ForkJoin(arreglo, 0, arreglo.length - 1));
        return resultado;
    }
}
