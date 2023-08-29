
package clases;
import java.util.Random;

public class main {
    public static void main(String[] args) {
       
        Random random1 = new Random(); //Instanciamos un objeto del metodo Random, para crear numeros aleatorios
        
        int matriz[][] = new int[3][3]; //Creamos una matriz bidimencional de tipo entero con dimensiones 3x3
        
        System.out.println("La matriz generada por la funcion Random");
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                matriz[i][j] = random1.nextInt(100); //Accedemos a la matriz bidemensional donde asignamos los valores a i y a j desde el 0 al 99
                System.out.print("\t"+"|"+matriz[i][j]+ "|");
            }
            System.out.println();
        }
        
        System.out.println("\n");
        
        //Intancia de los Hilos
        Hilo hilo1 = new Hilo(matriz, new int[]{1, 2}, 2); // Primer hilo multiplica las columnas 1 y 3 por 2
        Hilo hilo2 = new Hilo(matriz, new int[]{0}, 2);    // Segundo hilo multiplica la columna 2 por 2
        
        hilo1.start();
        
        //El join es para esperar que el hilo principal termine su ejecucion.
        try{          
            hilo1.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
       
        
        hilo2.start();
        
        try{          
            hilo2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        /*try-catch se asegura de que el hilo principal espere hasta que hilo1 
        termine antes de continuar. Si ocurre alguna interrupción 
        mientras se espera, se captura la excepción y se imprime su traza.*/
        
        //Resultado de la matrix
        System.out.println("La matriz resultado de la multiplicacion de los dos hilos");
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3; j++){
                System.out.print("\t"+"|"+matriz[i][j] + "|");
            }
            System.out.println();
        }
    }
}
