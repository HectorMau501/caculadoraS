
package Clases;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


public class ForkJoin extends RecursiveTask<ResultadoSuma> { //La clase RecursiveTask es para realizar tareas en paralelo y devolvera un tipo de dato
    
    private float[] arreglo; 
    private int inicio; 
    private int fin; 
    
    public ForkJoin(float[] arreglo, int inicio, int fin){ //Constructor
        this.arreglo = arreglo;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected ResultadoSuma compute() { //compute para el forkJoin
        
        long tiempoInicio = System.nanoTime();
        
        if(fin - inicio <= 1000){
            float suma = 0; //Se inicializa si la variable de suma
            for(int i = inicio; i <= fin; i++){ //Aquí se realiza un bucle que recorre y suma los elementos uno por uno a la variable suma
                suma += arreglo[i];
            }
            
            long tiempoFin = System.nanoTime(); 
            long tiempoEjecucion = tiempoFin - tiempoInicio; 
            
            return new ResultadoSuma(suma, tiempoEjecucion); 
            
        }else{
            int medio = (inicio + fin)/2;
             // Crea dos tareas ForkJoin para la parte izquierda y derecha
            ForkJoin izquierda = new ForkJoin(arreglo, inicio, medio);
            ForkJoin derecha = new ForkJoin(arreglo, medio+1, fin);
            izquierda.fork(); //Inicia la tarea en paralelo
            
            ResultadoSuma sumDerecha = derecha.compute();// Calcula manera recursiv
            ResultadoSuma sumIzquierda = izquierda.join();
            
            long tiempoFin = System.nanoTime(); 
            long tiempoEjecucion = tiempoFin - tiempoInicio; 

            return new ResultadoSuma(sumDerecha.getSuma() + sumIzquierda.getSuma(), tiempoEjecucion);        
        }              
    }
    
        //framework Fork/Join para dividir y calcular la suma de los elementos del arreglo en paralelo 
    public static ResultadoSuma sumarElementosConForkJoin(float[] arreglo) {
        ForkJoinPool pool = new ForkJoinPool(); //piscina de hilos
        ResultadoSuma resultado = pool.invoke(new ForkJoin(arreglo, 0, arreglo.length - 1));
        return resultado;
    }
}
