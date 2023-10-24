
package Clases;

public class Merge {
    
    //metodo MergeSort donde dividimos en dos mitades por separado y luego las juntamos, sumamos todo lo que haya en el 
    public static float mergeSort(float[] arreglo, int izquierda, int derecha) { //Arreglo que va ordenar y sumar y despues indice 
        //que va ordenar por la izquierda y derecha

        long tiempoEjecutar = System.nanoTime();
        if (izquierda < derecha) { // Determinamos si el subarreglo es lo suficientemente grande como para ser dividido nuevamente
            int medio = (izquierda + derecha) / 2; // Calculamos el punto medio del subarreglo
            float sumaIzquierda = mergeSort(arreglo, izquierda, medio); // Suma de la parte izquierda
            float sumaDerecha = mergeSort(arreglo, medio + 1, derecha); // Suma de la parte derecha
            
            float sumaTotal = sumaIzquierda + sumaDerecha; 
            return sumaTotal;
        }
        long finalTiempo = System.nanoTime();
        return arreglo[izquierda]; // Devolvemos el valor del elemento en lugar del tiempo
    }

    // Combinamos dos subarreglos
    public static float merge(float[] arreglo, int izquierda, int medio, int derecha) {
        float sumaIzquierda = 0;
        float sumaDerecha = 0;

         // Calcula la suma de la parte izquierda del subarreglo
        for (int i = izquierda; i <= medio; i++) {
            sumaIzquierda += arreglo[i];
        }

        // Calcula la suma de la parte derecha del subarreglo
        for (int i = medio + 1; i <= derecha; i++) {
            sumaDerecha += arreglo[i];
        }

        //Combinamos las sumas 
        arreglo[izquierda] = sumaIzquierda + sumaDerecha;
        return arreglo[izquierda];//Devuelve el valor combinado
    }
}


