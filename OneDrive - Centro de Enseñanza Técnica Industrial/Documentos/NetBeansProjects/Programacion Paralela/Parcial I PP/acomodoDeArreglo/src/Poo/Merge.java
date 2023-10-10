
package Poo;


public class Merge {
    public static long mergeSort(int[] arreglo, int izquierda, int derecha){ //arreglo izquierda(inicio del subarrreglo) y derecha(final..)
        
        long tiempoEjecutar = System.currentTimeMillis(); //Registra el tiempo utilizando el metodo de current
        
        if(izquierda < derecha){ //Determinar si el subarreglo es lo suficientemente grande como para ser dividido nuevamente
            
            int medio = (izquierda + derecha) / 2; //Calculamos el punto medio del subarreglo
            
            mergeSort(arreglo, izquierda, medio); //Mandamos a llamar a margeSort para ordenar de izquierda hasta el punto medio
            mergeSort(arreglo, medio + 1, derecha);  //
            
            merge(arreglo, izquierda, medio, derecha); //lla,a a merge para combinar las mitades y guardarlas en el arreglo
        }
        
        long finalTiempo = System.currentTimeMillis(); //Registramos el tiempo, pero el final
        return finalTiempo - tiempoEjecutar; //Calculamos el tiempo que tardo en ejecutar
    }
    
    // Combinamos dos subarreglos ordenados en uno solo ordenado.
    public static void merge(int[] arreglo, int izquierda, int medio, int derecha){
        
        int n1 = medio - izquierda + 1; //arreglo izquierda
        int n2 = derecha - medio;       //arreglo derecha
        
        //Creamos dos subarreglos temporales para almacenar temporalmente los elementos de los subarreglos.
        int[] izquierdaArr = new int[n1];
        int[] derechaArr = new int[n2];
        
        //Izquierda  rastrear las posiciones actuales en los subarreglos y en el arreglo original.
        for(int i = 0; i < n1 ; i++){
            izquierdaArr[i] = arreglo[izquierda + i];
        }
        //Derecha
        for(int j = 0; j < n2 ; j++){
            derechaArr[j] = arreglo[medio + 1 + j];
        }   
        
        //k Es el arreglo original arr
        
        int i = 0, j = 0, k = izquierda;
        
        //Se entra a este arreglo sabiendo que hay elementos que son menos de i <n1 en el subarreglo de la izquierda  e igual con el de la derecha
        while(i < n1 && j < n2){
            if(izquierdaArr[i] <= derechaArr[j]){
                arreglo[k] = izquierdaArr[i];
                i++;
            }else{
                arreglo[k] = derechaArr[j];
                j++;
            }
            k++;
        }
        //Se utilizan dos bucles while adicionales para copiar cualquier 
        //elemento restante del subarreglo izquierdo o derecho a
        while(i < n1){
            arreglo[k] = izquierdaArr[i];
            i++;
            k++;       
        }
        while(j < n2){
            arreglo[k] = derechaArr[j];
            j++;
            k++;
        }
    }
}
