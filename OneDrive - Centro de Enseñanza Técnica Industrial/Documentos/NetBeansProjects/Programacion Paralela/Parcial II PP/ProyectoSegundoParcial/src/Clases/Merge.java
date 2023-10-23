
package Clases;

public class Merge {
    public static float mergeSort(float[] arreglo, int izquierda, int derecha) {

        long tiempoEjecutar = System.nanoTime(); // Registra el tiempo

        if (izquierda < derecha) { // Determinamos si el subarreglo es lo suficientemente grande como para ser dividido nuevamente
            int medio = (izquierda + derecha) / 2; // Calculamos el punto medio del subarreglo
            float sumaIzquierda = mergeSort(arreglo, izquierda, medio); // Suma de la parte izquierda
            float sumaDerecha = mergeSort(arreglo, medio + 1, derecha); // Suma de la parte derecha
            float sumaTotal = sumaIzquierda + sumaDerecha; // Suma total
            return sumaTotal;
        }
        long finalTiempo = System.nanoTime();
        return arreglo[izquierda]; // Devolvemos el valor del elemento en lugar del tiempo
    }

    // Combinamos dos subarreglos ordenados en uno solo ordenado.
    public static float merge(float[] arreglo, int izquierda, int medio, int derecha) {
        float sumaIzquierda = 0;
        float sumaDerecha = 0;

        for (int i = izquierda; i <= medio; i++) {
            sumaIzquierda += arreglo[i];
        }

        for (int i = medio + 1; i <= derecha; i++) {
            sumaDerecha += arreglo[i];
        }

        arreglo[izquierda] = sumaIzquierda + sumaDerecha;
        return arreglo[izquierda];
    }
}


